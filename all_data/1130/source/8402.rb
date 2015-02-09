# ------------------------ PART 1 -------------------------------
def palindrome?(str)
  exp = /[\W]+/
  return str.downcase.gsub(exp, "") == str.reverse.downcase.gsub(exp, "")
end

def count_words(str)  
  exp = /\b([\w]+)\b/
  counts = {}
  str.downcase.gsub(exp) { |s| counts[s] = counts.has_key?(s) ? counts[s] + 1 : 1 }
  return counts
end

#puts count_words("A man, a plan, a canal -- Panama")

# ------------------------ PART 2 -------------------------------
class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def rps_game_winner(game)

  raise WrongNumberOfPlayersError unless game.length == 2

  p1 = [game[0][0], game[0][1].upcase]
  p2 = [game[1][0], game[1][1].upcase]

  strategies = ["R", "S", "P"]
  raise NoSuchStrategyError unless ( strategies.include?(p1[1]) && strategies.include?(p2[1]) )

  outcomes = {
    "RS" => 0, 
    "RP" => 1, 

    "SR" => 1, 
    "SP" => 0, 

    "PR" => 0,
    "PS" => 1,

    "PP" => 0,
    "SS" => 0,
    "RR" => 0

  }

  return game[outcomes[p1[1]+p2[1]]]
end

# g1 = [ [ "Armando", "S" ], [ "Dave", "S" ] ]
# puts rps_game_winner(g1)

def rps_tournament_winner(tournament)

  if tournament[0][0].kind_of?(String)
    return rps_game_winner(tournament)
  else
    return rps_tournament_winner([rps_tournament_winner(tournament[0]), rps_tournament_winner(tournament[1])])
  end

end

# t1 = 
# [
#  [
#    [ ["Armando", "P"], ["Dave", "S"] ],
#    [ ["Richard", "R"],  ["Michael", "S"] ],
#  ],
#  [ 
#    [ ["Allen", "S"], ["Omer", "P"] ],
#    [ ["David E.", "R"], ["Richard X.", "P"] ]
#  ]
# ]
# puts rps_tournament_winner(t1)


# ------------------------ PART 3 -------------------------------
def combine_anagrams(words)

  anagrams = Hash.new { |h,k| h[k] = [] }

  words.map { |word|
    anagrams[word.downcase.split('').sort] << word
  }

  return anagrams.values
end

i = ['CaRs', 'fOr', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(i)[0]



# ------------------------ PART 4 -------------------------------
class Class
  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s
    attr_reader attr_name
    attr_reader attr_name+"_history"
    class_eval %Q'
      def #{attr_name}=(value)
        if !defined? @#{attr_name}_history
          @#{attr_name}_history = [nil]
        end
        @#{attr_name} = value
        @#{attr_name}_history << value
      end

      def #{attr_name}
        @#{attr_name}
      end

      def #{attr_name}_history
        @#{attr_name}_history
      end'
  end
end

#words = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
#puts combine_anagrams(words).length