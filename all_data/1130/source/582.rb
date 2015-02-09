
# 1a. palindrome

def palindrome?(string)

  string = string.gsub(/\W/, "").downcase
  return string.reverse == string

end

# 1b. count_words

def count_words(string)

  counts = Hash.new(0) # init to 0 so += will work

  # this expressly follows the homework's instructions for finding words
  words = string.scan(/\b.+?\b/).reject { |x| x.match(/\W/) }

  # but it's exactly the same (by the definitions of \b \w and \W) 
  # as the much more readable
  # words = string.scan(/\w+/)

  words.each { |word| counts[word.downcase] += 1 }
  return counts

end

# 2a. rps_game_winner

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def rps_game_winner(players)

  raise WrongNumberOfPlayersError unless players.length == 2
 
  loses_to = {"R" => "S", "S" => "P", "P" => "R"}
  raise NoSuchStrategyError unless loses_to.keys.include? players[0][1]
  raise NoSuchStrategyError unless loses_to.keys.include? players[1][1]

  return players[0][1] == loses_to[players[1][1]] ? players[1] : players[0]

end

# 2b. rps_tournament_winner

def rps_tournament_winner(data)

  # hw says we can assume well-formed data, so not checking it

  # if data[0] is a string, data is a single player; return him as winner
  return data if data[0].is_a? String

  # otherwise use recursion
  return rps_game_winner( [rps_tournament_winner(data[0]), 
                          rps_tournament_winner(data[1])] )

end

# 3. anagrams

def combine_anagrams(words)
  table = {}
  words.each do |word|
    key = word.downcase.scan(/\w/).sort.to_s
    table[key] = [] unless table[key]
    table[key] << word
  end
  return table.values
end

# 4a. Dessert

class Dessert
  attr_accessor :name, :calories

  def initialize(name, calories)
    @name = name
    @calories = calories
  end
  
  def healthy?
    return @calories < 200
  end

  def delicious?
    return true
  end

end

# 4b. JellyBean

class JellyBean < Dessert
  attr_accessor :flavor

  def initialize(name, calories, flavor)
    super(name, calories)
    @flavor = flavor
  end

  def delicious?
    return "black licorice" == @flavor ? false : super()
  end

end

# 5. attr_accessor_with_history

# Define a method attr_accessor_with_history that provides the same functionality as attr 
# accessor but also tracks every value the attribute has ever had
class Class

  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s   # make sure it's a string
    attr_reader attr_name        # create the attribute's getter
    attr_reader attr_name+"_history" # create bar_history getter
    class_eval %Q{
      def #{attr_name}=(value)
        @#{attr_name} = value
        @#{attr_name}_history = [nil] unless @#{attr_name}_history
        @#{attr_name}_history << value
      end
    }
  end
  
end

# 5a. 
class Numeric
  @@currencies = {'yen' => 0.013, 'euro' => 1.292, 'rupee' => 0.019, 'dollar' => 1}
  def method_missing(method_id)
    singular_currency = method_id.to_s.gsub( /s$/, '')
    if @@currencies.has_key?(singular_currency)
      self * @@currencies[singular_currency]
    else
      super
    end
  end
  def in(currency)
    singular_currency = currency.to_s.gsub( /s$/, '')
    if @@currencies.has_key?(singular_currency)
      self / @@currencies[singular_currency]
    else
      super
    end
  end

end

# 5b. String.palindrome?
class String
  def palindrome?
    string = self.gsub(/\W/, "").downcase
    return string.reverse == string
  end
end

# 5c. Enumerable.palindrome?
module Enumerable
  def palindrome?
    return respond_to?(:reverse) ? self == reverse : nil
  end
end

# 6 CartesianProduct

class CartesianProduct
  include Enumerable
  # your code here
  def initialize(as, bs)
    @as = as
    @bs = bs
  end
  def each
    @as.each { |a| @bs.each { |b| yield([a, b]) } }
  end
end

