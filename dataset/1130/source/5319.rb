def palindrome?(string)
  justChars = string.gsub(/\W/i, '').downcase
  justChars == justChars.reverse 
end

def count_words(string)
  words = string.downcase.split(/\b/).select{ |s| s =~ /\w/}
  freqs = Hash.new(0)
  words.each{|word| freqs[word] += 1}
  freqs
end

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  raise NoSuchStrategyError unless ((game[0][1] =~ /^[rps]$/i) and (game[1][1] =~ /^[rps]$/i))
  
  if(game[0][1].downcase == game[1][1].downcase)
    game[0]
  end
  
  if (game[0][1] =~ /r/i)
    if (game[1][1] =~ /p/i)
      return game[1]
    else
      return game[0]
    end
  elsif (game[0][1] =~ /p/i)
    if (game[1][1] =~ /s/i)
      return game[1]
    else
      return game[0]
    end
  else
    if (game[1][1] =~ /r/i)
      return game[1]
    else
      return game[0]
    end
  end
end

def rps_tournament_winner(tournament)
  if(tournament.flatten.length == 4)
    rps_game_winner(tournament)
  else
    rps_game_winner([rps_tournament_winner(tournament[0]), rps_tournament_winner(tournament[1])])
  end
end

def combine_anagrams(words)
    
  results = Array.new(0)
  words.each do |word|
    found = false
    results.each do |result|
      if(result[0].downcase.chars.sort.join == word.downcase.chars.sort.join)
        result << word
        found = true
      end
    end
    if(not(found))
      results << [word]
    end
  end
  
  return results
end

class Dessert
  def initialize(name, calories)
    @name = name
    @calories = calories
  end
  
  def healthy?
    @calories < 200
  end
  
  def delicious?
    true
  end
end
class JellyBean < Dessert
  def initialize(name, calories, flavor)
    super(name, calories)
    @flavor = flavor
  end
  
  def delicious?
    if(flavor == "black licorice")
      return false
    end
    super
  end
end