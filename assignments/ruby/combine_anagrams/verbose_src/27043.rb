# ----------- CS169 Homework assignment 1
# by BLB 2012Jun04
# -----  CS169hw1.1.a  -----
def palindrome?(string)
   y = string.downcase.gsub(/\W/, '')
   y == y.reverse
end

# -----  CS169hw1.1.b
def count_words(string)
   y = string.downcase.scan(/\w+\b/)
   y.reduce(Hash.new(0)) {|h,x|
      h[x] += 1
      h}
end

# -----  CS169hw1.2.a  -----
class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end
def rps_game_winner(game)
   raise WrongNumberOfPlayersError unless 2 == game.length
   plays = 'RPS'                           #   Rock,     Paper,   Scissors
   r = [[0, 1, 0], [0, 0, 1], [1, 0, 0]]   #  [r P s,    r p S,    R p s ] results
   i = game.collect {|hand|                #  index into results
      raise NoSuchStrategyError unless 0 == (hand[1] =~ /[#{plays}]\b/i)
      plays.index hand[1].upcase}
   game[r[i[0]][i[1]]]                     #  return winning game
end

# -----  CS169hw1.2.b
def rps_tournament_winner(tournament)
   if Array == tournament[0][1].class      #  if match then pairoff
      winners = tournament.collect {|pairoff| 
         rps_tournament_winner(pairoff)}   #     recursive call for pairoff 
      rps_tournament_winner(winners)       #  recursive call for winners of pairoff
   else 
      rps_game_winner(tournament)          #  return winning game
   end
end

# -----  CS169hw1.3  -----
def combine_anagrams(words)
   words.reduce(Hash.new([])) {|anagrams, aword| 
      group = aword.downcase.chars.sort.join
      anagrams[group] = [*anagrams[group], aword]
      anagrams}.values
end

# -----  CS169hw1.4.a  -----
class Dessert
   def initialize(name, calories=0)
      @calories = calories
      @name = name
   end
   attr_accessor :name
   attr_accessor :calories
   
   def healthy?
      calories < 200
   end
   
   def delicious?
      true
   end
end

# -----  CS169hw1.4.b 
class JellyBean < Dessert
   def initialize(name, calories=0, flavor="black licorice")
      @flavor = flavor
      @calories = calories
      @name = name
   end
   attr_accessor :flavor
   
   def delicious?
      if @flavor == 'black licorice'
         false
      else
         super
      end
   end
end

# -----  CS169hw1.5  -----
class Class
   def attr_accessor_with_history(attr_name)
      name = attr_name.to_s            # make sure name attribute is a string
      hist = name + '_history'         # bar history attribute 
      attr_reader name                 # create the attribute's getter
      attr_reader hist                 # create bar_history getter
      class_eval %Q{
         def #{name}=(value) 
            @#{name} = value
            if defined? @#{hist}
               @#{hist} = [*@#{hist}, value] 
            else
               @#{hist} = [nil, value] 
            end
         end
      }
   end
end

# -----  CS169hw1.5a
class Numeric
   @@currencies = {'dollar' => 1.0, 'yen' => 0.013, 'euro' => 1.292, 'rupee' => 0.019}
   def in(currsymb)
      singular_currency = currsymb.to_s.gsub( /s$/, '')
      if @@currencies.has_key?(singular_currency)
         self / @@currencies[singular_currency]
      end
   end
   def method_missing(method_id)
      singular_currency = method_id.to_s.gsub( /s$/, '')
      if @@currencies.has_key?(singular_currency)
         self * @@currencies[singular_currency]
      else
         super
      end
   end
end

# -----  CS169hw1.5b
class String
   def palindrome?
      a = self.downcase.gsub(/\W/, '')
      a == a.reverse
   end
end

# -----  CS169hw1.5c
module Enumerable
   def palindrome?
      if self.class == Hash
         a = self.values
      else
         a = self.collect{|x| x}
      end
   a == a.reverse
   end
end

# -----  CS169hw1.6  -----
class CartesianProduct
   include Enumerable
   def initialize(x, y)
      @x = x;  @y = y
   end
   def each       
      @x.each do |a|
         @y.each do |b|
            yield [a, b]
         end                    
      end                    
   end 
end 
