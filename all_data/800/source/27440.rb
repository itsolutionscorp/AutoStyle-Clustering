##### Homework 1 
##### Alexander Rao 

require "pp"


puts "hello"," ", "world" 


#### Problem 1 part a

def palindrome?(string)

  clean = string.downcase.gsub(/[^a-z]+/,"")
  clean == clean.reverse

end

 
puts palindrome?("A man, a plan, a canal -- Panama") #=> true
puts palindrome?("Madam, I'm Adam!") # => true
puts palindrome?("Abracadabra") # => false (nil is also ok)


#### Problem 1 part b

def checker1(word,df)
  if df[word]
    df[word] = df[word] + 1
  else 
    df[word] = 1
  end 

end

## just wrote a quick checker to clean up the code in
## count words 


def count_words(string)
  
  result = {}

  clean = string.downcase.gsub(/[^a-z]+/," ").split
  clean2 = clean.each{|d| checker1(d,result)}
  
  return(result)
                                

end

puts count_words("A man, a plan, a canal -- Panama")
# => {'a' => 3, 'man' => 1, 'canal' => 1, 'panama' => 1, 'plan' => 1}
puts count_words "Doo bee doo bee doo" # => {'doo' => 3, 'bee' => 2}




#### Problem 2 part a 


class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError ; end


## this member expects a word or number for item 
## and an array of similar data type and checks 
## if the item is in the array 

def member(item,group)
  n = group.length
  
  if group.length == 0
    nil
  elsif item == group[0]
    true
  else
    member(item,group[1,n])
    
  end
  
end

 

def getstrat(player,game)
  return(game[player][1])
end

def getname(player,game)
  return(game[player][0])
end

def winner(player,game)
  return([getname(player,game),getstrat(player,game)])
end



def rps_game_winner(game)
  print "Game",game.inspect,"\n"
  raise WrongNumberOfPlayersError unless game.length == 2
  raise NoSuchStrategyError unless member(getstrat(0,game), ["R","P","S"])
  raise NoSuchStrategyError unless member(getstrat(1,game), ["R","P","S"])
  play1 = getstrat(0,game)
  play2 = getstrat(1,game)
  
  if play1 == play2
    winner(0,game)
  elsif play1 == "R"
    if play2 == "S"
      winner(0,game)
    else 
      winner(1,game)
    end
  elsif play1 == "P"
    if play2 == "R"
      winner(0,game)
    else 
      winner(1,game)
    end 
  else
    if play2 == "P"
      winner(0,game)
    else 
      winner(1,game)
    end 
  end

end


 puts rps_game_winner([ [ "Armando", "P" ], [ "Dave", "S" ] ]).inspect
 # => returns the list ["Dave", "S"] wins since S>P


print "Doing tournament\n"







def rps_tournament_winner(tournament)

  if tournament[0][0].class == Array
    rps_game_winner([rps_tournament_winner(tournament[0]),
                     rps_tournament_winner(tournament[1])])
  else 
    rps_game_winner(tournament)
  end
end 




a = [
     [
      [ ["Armando", "P"], ["Dave", "S"] ],
      [ ["Richard", "R"], ["Michael", "S"] ],
     ],
     [
      [ ["Allen", "S"], ["Omer", "P"] ],
      [ ["David E.", "R"], ["Richard X.", "P"] ]
     ]
    ]


puts(rps_tournament_winner(a).inspect)




##### Problem 3 part a



def helper(thing)
  clean = thing.downcase.split(//)
  [clean.sort,thing]
end 

def getkey(a)
a[0]
end

def getval(a)
a[1]
end

def checker(item, item2)
  key = getkey(item)
  if  key == getkey(item2)
    true
  else 
    false
  end
end 

def scan(scanner,strs)
  result = []
  strs.each{|s| 
    if checker(scanner,s)
      result.push(getval(s))
    end}
  return(result)
end

def cleaner(scanner,strs)
  strs.each{|s|
    if checker(scanner,s)
     strs.delete(s)
    end}
  return(strs)
end


def combine_anagrams(str)
  str.map{|d| helper(d)}
  
  if str.length == 0 
    []
  else
    [scan(str[0],str[1,str.length]),
     combine_anagrams(cleaner(str[0],str[1,str.length]))]
  end


end



puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 
              'four','scar', 'creams', 'scream']).inspect







### Problem 4

class Dessert
  def initialize(name, calories)
   @name = name
   @calories = calories
  end
  
  def name
    @name
  end

  def calories
    @calories
  end

  def name=(new_name)
    @name=new_name
  end

  def calories=(new_amount)
    @calories=new_amount
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
  def initialize(name, calories, flavor)
    @flavor = flavor
  end
  def flavor
    @flavor
  end
  def flavor=(new_flavor)
    @flavor = new_flavor
  end

  def delicious?
    if @flavor == "black licorice"
      false
    else
      true
    end
  end
end






### Problem 5 


class Class
  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s # make sure it's a string
    attr_reader attr_name # create the attribute's getter
    attr_reader attr_name+"_history" # create bar_history getter
    class_eval %Q[
                  def #{attr_name}=(val)
                    if @#{attr_name}_history == nil
                      @#{attr_name}_history = [@#{attr_name}]
                    end
                    @#{attr_name}_history << val
                    @#{attr_name} = val
                  end
                  
                  def #{attr_name}_history
                    @#{attr_name}_history
                  end
                  ]
  end

end


class Foo

  attr_accessor_with_history :bar

end


puts f = Foo.new
puts f.bar = 1
puts f.bar = 2
puts f = Foo.new
puts f. bar = 4
puts f.bar_history








## Part A

class Numeric
  @@currencies = {'dollar'=>1,'yen' => 0.013, 
    'euro' => 1.292, 'rupee' => 0.019}
  
  def in(obj)
    rate = @@currencies[obj.to_s.gsub( /s$/, '')]
    self/rate
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


5.dollars.in(:euros)
10.euros.in(:rupees)





## Part B


class String 
  def palindrome?
    clean = self.downcase.gsub(/[^a-z]+/,"")
    clean == clean.reverse
  end
end 



## Part C 


#### come back to 
module Enumerable 
  def palindrome?
    self == self.reverse
  end
end

















##### Problem 6

class CartesianProduct
  include Enumerable
  
  def initialize(a,b)
    @a = a 
    @b = b 
  end
  
  def each
    
    @a.each do |x| 
      @b.each do |y|
       yield [x,y]
      end
    end
      
    
  end


end







#Examples of use
c = CartesianProduct.new([:a,:b], [4,5])
c.each { |elt| puts elt.inspect }
# [:a, 4]
# [:a, 5]
# [:b, 4]
# [:b, 5]
c = CartesianProduct.new([:a,:b], [])
c.each { |elt| puts elt.inspect }
# (nothing printed since Cartesian product
# of anything with an empty collection is empty)
