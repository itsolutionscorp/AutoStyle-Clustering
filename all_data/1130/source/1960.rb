def combine_anagrams(words)
  anagramHash = Hash.new(0)
  words.each do |aWord|
    hashKey = aWord.downcase.chars.sort
    if anagramHash[hashKey] == 0 then 
      anagramHash[hashKey] = []
    end
    anagramHash[hashKey] += [aWord]
  end
  # anagramHash.values.each do |anArray|
  #  anArray.uniq!
  # end
  anagramHash.values
end

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end
def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  # your code here
   p1s = game[0][1]
   p2s = game[1][1]
   raise NoSuchStrategyError unless (p1s == "R" or p1s == "P" or p1s == "S")
   raise NoSuchStrategyError unless (p2s == "R" or p2s == "P" or p2s == "S") 
   winner = game[0]
   if (p2s == "R" and p1s == "S") or 
     (p2s == "P" and p1s == "R") or 
     (p2s == "S" and p1s == "P") then winner = game[1]
   end
   winner
end

def rps_tournament_winner(tree)
  if tree[0] == tree[0].flatten then rps_game_winner(tree)
  else rps_game_winner([ rps_tournament_winner(tree[0]), rps_tournament_winner(tree[1]) ])
  end
end

def palindrome?(string)
  newString = string.downcase.gsub(/[^a-zA-Z]/, '')
  newString == newString.reverse
end

def count_words(string)
  wordArray = string.gsub(/[^\w ]/, '').scan(/\w+/)
  countHash = Hash.new(0)
  wordArray.each do |aWord|
       countHash[aWord] += 1
  end
  countHash
end
