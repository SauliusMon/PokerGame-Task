# PokerGame-Task

## How it works:

Program begins by reading poker.txt file (location is declared in main class) with a scanner and while loop. At every line read, string is split into String[] array, which in this game consists of 10 ellements with 2 symbols each (10 cards for 2 players). Those 10 ellements are converted into Cards, which are later put into separate PlayerCardDecks. In this case, PlayerCardDecks consist of 5 cards each and have a player name, while cards inside the deck hold value of the card (Integer) and a card suit (custom made Enum). After making PlayerCardDecks (in this game - 2 separate Deck objects), they are passed into hand rating method, which returns RankedPlayerHand object. This object is used to get a hand power ranking integer (hand combination - e.g. Full House is equal to 7) and to compare hands in a case, where both hands have an equal previously mentioned combination (hand) power  rank. After comparing those hands, the winnner is determined. Even though in a task description it is written, that every case has a clear winner and objective is to show how many games "Player 1" won, I decided to implement functionality for a tie (for example, both hands have a Royal Flush) and calculate score for both Players. I came to a conclusion, that ,,Player 1" won 376 times, while ,,Player 2" won 624 times. Those points are being caclulated every time line is read from the file and are stored as a global static private primitive ints inside a PlayPoker class.


## Object-oriented programming ideas used in this task:

* __CardValue enum:__ 
Used to convert card enum to an integer value (for example, T would be 10, J would be 11 and etc...). 
* __CardSuit enum:__ 
Used to hold a card suits, which are: H, D, C, S.

* __Card:__
Represents a card in the game. Holds value and suit which are instantiated by a constructor. 
Custom made comparator: it compares card values, and, if card values match, it compares card suit.ordinal() integers. Because of the comparator, Cards are sorted by ascending value, while sorting with suit.ordinal() prevents Set collection from denying cards which differ by their suit only.
* __CardDeck:__ 
  Represents a hand in the game. The maximum size of CardDeck is defined by integer which is passed to the constructor. This is used to check whether CardDeck is full or not. In this particular poker game, maximum side could always be set to 5, though it diminishes code ,,flexibility''. AddCard and AddCards methods only add card when the CardDeck size doesnt exceed it's maximum capacity. CanAddCards checks if CardDeck can accept more members.
* __PlayerCardDeck:__ 
  Child of the CardDeck class, and as a addition, it can also hold a name of the Player.
* __RankedPlayerHand:__ 
* Used to determine winning hand. It holds a power ranking integer (deckRanking), ranked cards TreeSet (cards which were used to determine hand ranking) and unranked cards TreeSet (cards which were redundant to hand rank). Compare method in this class checks, whether hands being compared hold the same power ranking integer, and, if they don't, comparator returns the winner (1 or -1). If they do and they both have a RoyalFlush, comparator returns 0. In other cases, comparator starts comparing TreeSet of Ranked Cards and TreeSet of Unranked Cards to find a high card and determine a winner. if nn a very rare case hands are still even, comparator returns 0.

  
## What I like and dislike about my solution:

I think my solution could be easier to read and more efficient in terms of performance. I tried using HashMap and TreeSet collections for a performance boost and iterate through them only when necessary, but there might be a more efficient solution to check hands ranking, compare them and convert a String from the text file to an object of Card.
I liked that my code was pretty ,,flexible'', even though task didn't require it. In a case where text file has 15 cards and 3 players, or 6 cards for 2 players, it should be pretty easy to change the code so it would work even in those circumstances.

  
## New technologies or approaches:

Pretty much with everything I used in this task I had some prior experience. Though, I could say that this was the first time that I used TreeSet collection in practice which was pretty interesting. I thought that this java Set suited task very well, because sorting cards beforehand would make hand ranking easier and all cards had were unique.
