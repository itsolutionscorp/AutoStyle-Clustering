def palindrome?(string)
  string = string.downcase.gsub(%r"[^a-z]", '')
  string[0..string.length/2-1] == string.reverse[0..string.length/2-1]
end

def count_words(string)
  string = string.downcase.gsub(%r"[^a-z\b\s]", '')
  ret = {}
  ret.default = 0
  string.split(%r"[\b\s]+").each do |el|
    ret[el] += 1
  end
  ret
end

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def is_strategy?(s)
  s != nil and s.downcase.match(%r"[rps]")
end

def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  raise NoSuchStrategyError unless is_strategy? game[0][1] and is_strategy? game[1][1]
  lose = {'s' => 'r', 'r' => 'p', 'p' => 's'}
  return game[0] unless lose[game[0][1].downcase] == game[1][1].downcase
  return game[1]
end

def rps_tournament_winner(tournament)
  return rps_game_winner tournament unless tournament[0][0].is_a? Array
  rps_game_winner([rps_tournament_winner(tournament[0]), rps_tournament_winner(tournament[1])])
end

def combine_anagrams(words)
  hash = {}
  hash.default = []
  words.each do |el|
    hash[el.downcase.chars.sort] += [el]
  end
  hash.values
end

# input: 
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) # #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
