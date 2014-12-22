#!/usr/bin/ruby



def palindrome?(string)
  fixed = string.gsub(/[\W]+/, '').downcase 
  fixed == fixed.reverse
end


def count_words(string)
  ret = {}
  fixed = string.downcase.split(/\b/).reject { |s| s =~ /[\W+]/ }
  fixed.each { |word|
    if ret.include?(word)
      ret[word] = ret[word] + 1
    else
      ret[word] = 1
    end
  }
  ret
end


class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  valid = ['R', 'P', 'S']
  p1play = game[0][1]
  p1play.upcase! unless p1play.nil?
  p2play = game[1][1]
  p2play.upcase! unless p2play.nil?
  raise NoSuchStrategyError unless valid.include?(p1play) and valid.include?(p2play)
  case p1play
  when 'R'
    case p2play
    when 'R'
      game[0]
    when 'P'
      game[1]
    when 'S'
      game[0]
    end
  when 'P'
    case p2play
    when 'R'
      game[0]
    when 'P'
      game[0]
    when 'S'
      game[1]
    end

  when 'S'
    case p2play
    when 'R'
      game[1]
    when 'P'
      game[0]
    when 'S'
      game[0]
    end

  end

end

def rps_tournament_winner(tournament)
  if tournament[0][0].class == String
    rps_game_winner(tournament)
  else
    win0 = rps_tournament_winner(tournament[0])
    win1 = rps_tournament_winner(tournament[1])
    rps_game_winner([win0] + [win1])
  end

end


def combine_anagrams(words)
  ret = []
  buff = []
  words.each { |word|
    base = word.downcase.chars.sort.join
    index = buff.index(base)
    if ! index.nil?
      ret[index] << word
    else
      ret << [word]
      buff << base
    end      
  }
  ret
end


class Dessert

  attr_accessor :name, :calories

  def initialize(name, calories)
    @name = name
    @calories = calories
  end

  def healthy?
    if @calories < 200
      true
    else
      false
    end
  end

  def delicious?
    true
  end

end

class JellyBean < Dessert
  attr_accessor :flavor

  def initialize(name, calories, flavor)
    super(name, calories)
    @flavor = flavor
  end

                                      

  def delicious?
    if flavor == 'black licorice'
      false
    else
      super
    end
  end

end

