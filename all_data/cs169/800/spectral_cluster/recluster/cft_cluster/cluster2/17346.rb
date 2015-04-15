def palindrome?(string)
  strippedString = string.downcase.gsub(/\W/, '')
  reversedString = strippedString.reverse
  return reversedString == strippedString
end

def count_words(string)
  strippedString = string.downcase.gsub(/\W/, ' ').gsub(/\s+/, " ").strip
  counts = Hash.new(0)
  strippedString.split(/\b/).each do |word|
    if !(word == " ")
      if counts.has_key?(word)
        counts[word]+=1
      else
        counts[word] = 1
      end
    end
  end
  counts
end

def rps_game_winner(games)

  winner = []
  raise WrongNumberOfPlayersError unless games.length == 2
  rasie WrongNumberOfInputsError unless games[0].length ==2
  rasie WrongNumberOfInputsError unless games[1].length ==2
  games.each do |game|
    if !(game[1].downcase == "r" ||game[1].downcase == "s" ||game[1].downcase == "p")
      raise NoSuchStrategyError
    end
    if games[0][1].downcase == 'r'&& games[1][1].downcase == 's'
      winner = games[0]
    elsif games[0][1].downcase == 's'&& games[1][1].downcase == 'p'
      winner =games[0]
    elsif games[0][1].downcase == 'p'&& games[1][1].downcase == 'r'
      winner = games[0]
    elsif games[0][1] == games[1][1]
      winner =games[0]
    else
      winner =games[1]
    end
  end
  winner
end

def contains_game(game)
  return game[0][0].is_a?(String)
end


def rps_tournament_winner(games)

  if contains_game(games)
    return rps_game_winner(games)
  end

  rps_game_winner [rps_tournament_winner(games[0]), rps_tournament_winner(games[1])]
end

def combine_anagrams(words)

  anagrams= Hash.new

  words.each do |word|
    if word.length == 1
      key = word.downcase
    elsif key = word.downcase.chars.sort.join
    end

    if (anagrams.has_key?(key))
      anagrams[key].push word
    else
      anagrams[key] = [word]
    end
  end

  anagrams.values
end

class WrongNumberOfPlayersError < StandardError;
end
class WrongNumberOfInputsError < StandardError;
end
class NoSuchStrategyError < StandardError;
end


p palindrome?("A man, a plan, a canal -- Panama") #=> true
p palindrome?("Madam, I'm Adam!") # => true
p palindrome?("Abracadabra") # => false (nil is also ok)

p count_words "Doo bee doo bee doo"
p count_words("what the what the what")

p rps_game_winner [["vijay", "S"], ["akhila", "P"]]

p rps_tournament_winner [[
                             [
                                 [["Armando", "P"], ["Dave", "S"]],
                                 [["XYZ", "R"], ["Michael", "S"]]
                             ],
                             [
                                 [["Allen", "S"], ["Omer", "P"]],
                                 [["David E.", "R"], ["Richard X.", "P"]]
                             ],
                             [
                                 [["Vijay", "P"], ["Akkineni", "S"]],
                                 [["Lokesh", "R"], ["Akkineni", "S"]]
                             ],
                             [
                                 [["Akhila", "S"], ["Kambhampati", "P"]],
                                 [["Spandana", "R"], ["Bondalapati", "P"]]
                             ],
                             [
                                 [["Armando", "P"], ["Dave", "S"]],
                                 [["Richard", "R"], ["Michael", "S"]]
                             ],
                             [
                                 [["Allen", "S"], ["Omer", "P"]],
                                 [["David E.", "R"], ["Richard X.", "P"]]
                             ],
                             [
                                 [["Vijay", "P"], ["Akkineni", "S"]],
                                 [["Lokesh", "R"], ["Akkineni", "S"]]
                             ],
                             [
                                 [["Akhila", "S"], ["Kambhampati", "P"]],
                                 [["Spandana", "R"], ["Bondalapati", "P"]]
                             ]
                         ], [
    [
        [["Armando", "P"], ["Dave", "S"]],
        [["XYZ", "R"], ["Michael", "S"]]
    ],
    [
        [["Allen", "S"], ["Omer", "P"]],
        [["David E.", "R"], ["Richard X.", "P"]]
    ],
    [
        [["Vijay", "P"], ["Akkineni", "S"]],
        [["Lokesh", "R"], ["Akkineni", "S"]]
    ],
    [
        [["Akhila", "S"], ["Kambhampati", "P"]],
        [["Spandana", "R"], ["Bondalapati", "P"]]
    ],
    [
        [["Armando", "P"], ["Dave", "S"]],
        [["Richard", "R"], ["Michael", "S"]]
    ],
    [
        [["Allen", "S"], ["Omer", "P"]],
        [["David E.", "R"], ["Richard X.", "P"]]
    ],
    [
        [["Vijay", "P"], ["Akkineni", "S"]],
        [["Lokesh", "R"], ["Akkineni", "S"]]
    ],
    [
        [["Akhila", "S"], ["Kambhampati", "P"]],
        [["Spandana", "R"], ["Bondalapati", "P"]]
    ]
]]

p combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

p combine_anagrams ['A', 'a']
