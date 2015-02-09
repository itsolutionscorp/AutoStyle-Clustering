#!/usr/bin/env ruby

# Florian Hassanen
# florian.hassanen@gmail.com

def palindrome?(string)
    q = string.downcase.gsub(%r{[^a-z]},'')
    return q == q.reverse
end

def count_words(string)
    q = %r{\b([a-z]+?)\b}

    r = Hash.new {0}
    string.downcase.scan(q).each { |m| r[m[0]] += 1 }

    return r
end

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end

def rps_game_winner(game)
    raise WrongNumberOfPlayersError unless game.length == 2

    game.each { |l| raise NoSuchStrategyError unless %r{^[rps]$}i =~ l[1] }

    r = Hash[ "r" => "s", "p" => "r", "s" => "p" ]
    
    p0 = game[0][1].downcase
    p1 = game[1][1].downcase

    return game[0] if p0 == p1 || r[p0] == p1
    return game[1]
end

def rps_tournament_winner(tourn)
    return tourn if tourn[0].class != Array

    return rps_game_winner( [rps_tournament_winner(tourn[0]), rps_tournament_winner(tourn[1])] )
end

def combine_anagrams(words)
    a = Hash.new { [] }

    words.each { |w| a[ w.downcase.split('').sort.join ] <<= w }

    return a.map { |k,v| v }
end

class Dessert
    attr_accessor :name, :calories
    
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
    attr_accessor :flavor

    def initialize(name, calories, flavor)
        super(name, calories)
        @flavor = flavor
    end
    def delicious?
        return false if @flavor == "black licorice"
        
        super
    end
end

class Class
    def attr_accessor_with_history(attr_name)
        attr_name = attr_name.to_s
        attr_hist = attr_name + "_history"
        # make sure it's a string
        attr_reader attr_name
        # create the attribute's getter
        attr_reader attr_hist # create bar_history getter
        
        class_eval %Q(
            
            def #{attr_name}=(value)

                @#{attr_hist} = [] << #{attr_name} if @#{attr_hist}.nil?
                @#{attr_name} = value
                @#{attr_hist} <<= value
            end
        )
    end
end

class Numeric
  @@currencies = {'yen' => 0.013, 'euro' => 1.292, 'rupee' => 0.019, 'dollar' => 1.0}
  def method_missing(method_id)
    singular_currency = method_id.to_s.gsub( /s$/, '')
    if @@currencies.has_key?(singular_currency)
      self * @@currencies[singular_currency]
    else
      super
    end
  end

  def in(target_curr)
    singular_currency = target_curr.id2name.gsub( /s$/, '')
    if @@currencies.has_key?(singular_currency)
        self / @@currencies[singular_currency]
    else
        super
    end
  end
end

String.send( :define_method, :palindrome? ) { Object.send( :palindrome? , self ) }
Enumerable.send( :define_method, :palindrome? ) { self.entries == self.entries.reverse }

class CartesianProduct
  include Enumerable
  # your code here

  def initialize(a, b)
    @a = a
    @b = b
  end

  def each
    @a.each { |aa| @b.each { |bb| yield [ aa , bb ] } }
  end
end

