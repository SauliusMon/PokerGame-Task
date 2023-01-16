# PokerGame-Task

How it works:

After initial start, this program begins reading poker.txt file (location is declared in main class) with a scanner and while loop. At every line read, string is split into String[] array, which in this game consists of 10 ellements with 2 symbols (5 cards for 2 players). Those 10 ellements are converted into Cards, which are later put into separate PlayerCardDecks. In this case, PlayerCardDecks consist of 5 cards each and have a player name, while cards hold value of the card (Integer) and a card suit (custom made Enum). After making PlayerCardDecks (in this game - 2 separate Deck objects), they are passed into hand rating method, which returns RankedPlayerHand object. This object is used to get a hand ranking integer (hand combination - e.g. Full House is equal to 7) and to compare hands in a case, where both hands have an equal previously mentioned combination (hand) rank. After comparing those hands, the winnner is determined. Even though in a task description it is written, that every case has a clear winner and I only need to show how many games "Player 1" won, I decided to implement functionality for a tie (for example, both hands have Royal Flush) and calculate score for both Players. I came to a conclusion, that ,,Player 1" won 376 times, while ,,Player 2" won 624 times. Those points are being caclulated every line read from file and are stored as a global static private primitive ints in a class.

Object-oriented programming ideas used in this task:

As i meantioned before, I used a couple of different java objects. For starters, 2 enum classes were used to hold and convert Char values to enums.
One of those was CardValue enum - which was used to convert card enum to an integer value (for example, T would be 10, J would be 11 and etc...). On the other hand, CardSuit enum doesn't have such functionality - its only used to hold a card suits, which are: H, D, C, S.
Other java objects that were used are pretty self explanitory aswell - it would be Card, CardDeck, PlayerDeck and RankedPlayerHand. 
Card would hold a final int value of card value and an final enum of card suit. These values would be assigned by instantiating card and passing aforementioned values. Card object also had getters, toString methods, and an custom comparator. While getters and toString methods are pretty self-explanatory, comparator should get a brief explanation: firstly, it compares card values and secondly, if card values match, it compares card suit.ordinal() integers. This comparator is used in an CardDeck object, which holds a TreeSet<Card> collection. Because of the comparator, Cards are sorted in aforestated collection by ascending value, while sorting with suit.ordinal() prevents TreeSet from denying new members entry, which hold a card value which is already present in a set.
CardDeck main purpose is to hold Set of unique cards. The maximum size of CardDeck is made by passing integer to constructor. This is used to check whether CardDeck is full or not. In this particular poker game, maximum side could always be set to 5, though it diminishes code ,,flexibility''. It also has getter, toString, canAddCards, addCard and addCards methods. AddCard method adds one card to the CardDeck (if it doesn't exceed maximum size), AddCards does the same thing just with multiple cards (var args was used here). CanAddCards method is used to determine whether amount of cards program is trying to add doesn't exceed maximum size.
PlayerCardDeck is a child of CardDeck class, and can also hold a name of the Player. It has its own getter and toString methods.
RankedPlayerHand is an object used to compare hands. It holds a power ranking integer (hand ranking), ranked cards TreeSet (cards which were used to determine hand ranking) and unranked cards TreeSet (cards which were redundant to hand rank). It has its own getters and compare method. At first, compare method checks, whether hands being compared hold the same power ranking integer (in code int is called deckRanking). If they don't, comparator returs the number. If they do and they both have RoyalFlush, comparator returns 0. In other cases, comparator starts comparing TreeSet of Ranked Cards and tries to find the highest card here. If it fails (or the Ranked Cards TreeSet is empty, prevalent in a case of High Card), comparator starts compareing Unranked Cards TreeSet and looks for a highest card. In a case where all card values are even, comparator returns 0.

What I like and dislike about my solution:

I think my solution could be easier to read and more efficient in terms of performance. I tried using HashMap and TreeSet collections for performance boost and iterate through them only when necessary, but, for example, there might be a more efficient solution to check hands ranking, compare them and convertion of String from text file to Card object.
I liked that my code was pretty ,,flexible'', even though task didn't require it. In a case where text file had 15 cards and 3 players, or 6 cards for 2 players, it should be pretty easy to change the code so it would work even in those circumstances. Also, insted of using local variables, I declared them in main method as a static final global ones (File path, Players amount, Cards per line amount).

New technologies or approaches:

Pretty much with everything I used in this task I had some level of experience. Though, I could say that this was the first time that I used TreeSet collection in practice which was pretty intresting. I thought that this java set suited task very well, because sorting cards beforehand would make hand ranking easier and all cards had unique values.
