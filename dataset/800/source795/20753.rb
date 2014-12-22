def palindrome? string
  clear_text = string.gsub(/\s|\W|\d*/, "").downcase
  clear_text == clear_text.reverse
end

def count_words string
  clear_arr = []
  string.split(/\b/).each{|a| clear_arr << a.downcase if a.match(/\w/)}

  h = {}
  clear_arr.each{|e| h[e].nil? ? h.merge!(e => 1) : h.merge!(e => h[e] + 1)}
  h
end


def combine_anagrams words
  words.group_by{|w| w.downcase.chars.sort.to_s}.values
end


class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def rps_game_winner(game)
  # game = [ [ "Armando", "P" ], [ "Dave", "S" ] ]
  # => returns the list ["Dave", "S"] wins since S>P

  raise WrongNumberOfPlayersError unless game.length == 2
  # your code here
end