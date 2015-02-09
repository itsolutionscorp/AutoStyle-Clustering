#!/usr/bin/env ruby

def palindrome?(string)
    puts string
    newS = string.downcase.gsub!(/\W?/i, '')
    return newS==newS.reverse
end

def count_words(string)
#puts string
    string.downcase.split.each {|e| 
        if h.has_key?(e) 
          h[e]=1+h[e] 
        else 
          h[e]=1 
        end}
    puts h
end

#palindrome?("A man, a plan, a canal -- Panama")
#count_words("A man, a plan, a canal -- Panama")
#count_words("Doo bee doo bee doo")
class WrongNumberOfPlayersError < StandardError; end
class NoSuchStrategyError < StandardError; end
def rps_game_winner(game)
   raise WrongNumberOfPlayersError unless game.length == 2
   map = { "R" => "S", "P" => "R", "S" => "P"}
   game.each{|key, value| 
     raise NoSuchStrategyError unless map.has_key?(value)}

   if map[game[1][1]].downcase == game[0][1].downcase 
     return game[1] 
   else 
     return game[0] 
   end
end

def rps_tournament_winner(tourney)
   return tourney unless tourney.first.length == 2
   winners=Array.new
   tourney.each{|subT| 
         winners.push(rps_tournament_winner(subT))
       }
   return rps_game_winner(winners)
#   if tourney[0].length==2
#     winnerA=rps_game_winner(tourney[0])
#   else
#     winnerA=rps_tournament_winner(tourney[0])
#   end
end

a = [ [ "Armando", "P" ], [ "Dave", "S" ], [ "Fail", "S"]]
#rps_game_winner(a)
a = [ [ "Dave", "S" ] ]
#rps_game_winner(a)
a = [ [ "Armando", "X" ], [ "Dave", "S" ] ]
#rps_game_winner(a)
a = [ [ "Armando", "P" ], [ "Dave", "S" ] ]
#puts rps_game_winner(a)
a = [ [ "Armando", "S" ], [ "Dave", "P" ] ]
#puts rps_game_winner(a)
a = [ [ "Armando", "S" ], [ "Dave", "S" ] ]
#puts rps_game_winner(a)

tourney = [ ["Richard", "R"], ["Michael", "S"] ]

#puts rps_tournament_winner(tourney)
tourney =
[
[
  [ ["Armando", "P"], ["Dave","S"] ],
  [ ["Richard", "R"], ["Michael", "S"] ],
],
[
  [ ["Allen", "S"], ["Omer","P"] ],
  [ ["David E.", "R"], ["Richard X.", "P"] ]
]
]
#puts rps_tournament_winner(tourney)

tourney = [
  [ ["Allen", "S"], ["Omer","P"] ],
  [ ["David E.", "R"], ["Richard X.", "P"] ]
]


#puts rps_tournament_winner(tourney)
def combine_anagrams(input)
#puts input 
   result=Hash.new
   input.each{|word| 
      ana=word.downcase.scan(/./).sort
#puts ana
      list = result[ana]
      if !list
          list=Array.new
      end
      result[ana]=list.push(word)
    }
    return result.values
end
input = ['cars', 'for', 'potatoes', 'Racs', 'four', 'scar', 'creams', 'scream']
#puts combine_anagrams(input)
