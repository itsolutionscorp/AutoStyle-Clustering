#!/usr/local/bin/ruby 
# -*- coding: utf-8 -*-

require 'pp'
#require 'yaml'


# used to learn out non-alphabetical characters
def clean_string(string)
  return string.downcase.gsub(/[^a-z]/, '')
end

# determine if the string is a palindrome, returns bool
def palindrome?(string)
  string = clean_string(string)
  return (string==string.reverse)
end

# count number of words in the string using \b to define words, returns hash
def count_words(string)
  words = string.split(/\b/)
  count_hash = Hash.new(0)
  words.each do |word|
    word = clean_string(word)
    word = word.strip.squeeze(" ")
    next if word==" " or word.nil? or word.empty?
    count_hash[word] += 1
  end
  return count_hash
end

# determines if the stategy given is valid
def valid_rps(rps)
  rps = rps.downcase
  return (rps=='s'||rps=='p'||rps=='r')
end

# Rock-Paper-Scissors game
def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  rps1 = game[0][1].downcase
  rps2 = game[1][1].downcase
  raise NoSuchStrategyError unless (valid_rps(rps1)&&valid_rps(rps2))

  # ties go to the first person
  if (rps1==rps2 || (rps1=='s'&&rps2=='p') || (rps1=='p'&&rps2=='r') ||
      rps1=='r'&&rps2=='s')
    return game[0]
  else
    return game[1]
  end
end

# Rock-Paper-Scissors tournament
def rps_tournament_winner(tournament)
  if (tournament[0][0].class==String)
    winner = rps_game_winner(tournament)
#puts "match: #{tournament}"
#puts "result: #{winner}"
  else
    winner1 = rps_tournament_winner(tournament[0])
    winner2 = rps_tournament_winner(tournament[1])
    winner = rps_tournament_winner([winner1, winner2])
  end
  return winner
end

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end



# Anagrams
def combine_anagrams(words)
  collected = Hash.new(Array.new)
  words.each_index {|i| collected[words[i].downcase.split("").sort] += [words[i]]}
  return collected.values
end



# testing main
if __FILE__ == $0

# palindrome? testing
  test_strings = {"A man, a plan, a canal -- Panama"=>true,
                  "Madam, I'm Adam!"=>true,
                  "Abracadabra"=>false,
                  "ala"=>true}
  test_strings.each_pair do |string, answer|
    print string + " -- "
    if (palindrome?(string)==answer) 
      puts "right" 
    else 
      puts "wrong"
    end
  end  

# count_words testing
  count_test = {"A man, a plan, a canal -- Panama"=>
    {'a' => 3, 'man' => 1, 'canal' => 1, 'panama' => 1, 'plan' => 1}}
  count_test.each_pair do |string, answer|
    print string + " -- "
    if (count_words(string)==answer)
      puts "right"
    else
      puts "wrong"
    end
    result = count_words(string)
    PP.pp result
  end
  
# rps_game_winner testing
  rpsgames = {[ [ "Armando", "P" ], [ "Dave", "S" ] ] =>
              ["Dave", "S"]}
  rpsgames.each_pair do |game, result|
    PP.pp game
    if (rps_game_winner(game)!=result)
      puts "failed game"
    else
      puts "correct game"
    end
  end

# rps_tournament_winner, requires 2^n players
  test_tournaments = 
{
  [
    [
      [ ["Armando", "P"], ["Dave", "S"] ],
      [ ["Richard", "R"], ["Michael", "S"] ],
    ],
    [
      [ ["Allen", "S"], ["Omer", "P"] ],
      [ ["David E.", "R"], ["Richard X.", "P"] ]
    ]
  ]=>["Richard", "R"],
  [
    [ ["Armando", "P"], ["Dave", "S"] ],
    [ ["Richard", "R"], ["Michael", "S"] ],
  ]=>["Richard", "R"]
}
  test_tournaments.each_pair do |tournament, result|
    PP.pp tournament
    if (rps_tournament_winner(tournament)!=result)
      puts "failed tournament"
    else
      puts "correct tournament"
    end
  end

  test_anagrams = {['Cars', 'for', 'potatoes', 'racs', 'four',
    'scar', 'creams', 'scream']=>[["Cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]}
  test_anagrams.each_pair do |anagrams, result|
    PP.pp anagrams
    myresult = combine_anagrams(anagrams).sort!
    result.sort!
    if (myresult!=result)
      puts "failed anagrams"
    else
      puts "correct anagrams"
    end
  end
  

end

=begin
# in some other library
def around_stuff
  ..before code...
  yield
  ..after code...
end
# in your code
def do_everything
  around_stuff do
    my_custom_stuff()
  end
end
=end

