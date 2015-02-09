# PART 1

def palindrome?(string)
	string = string.downcase	
	string.gsub!(/\W/,'')
	midway = string.length/2 + string.length % 2 - 1
	return string[0..midway] == string[string.length/2..string.length].reverse
end

# palindrome?("A man, a plan, a canal -- Panama")  #=> true
# palindrome?("Madam, I'm Adam!")  # => true
# palindrome?("Abracadabra")  # => f

def count_words(s)
	s = s.downcase
	s.gsub!(/\W+/," ")
	s = s.split(" ")
	freqs = Hash.new(0)
	s.each{ |word| freqs[word] += 1 }
	return freqs
end

#count_words("A man, a plan, a canal- -- Panama")  #=> true

# PART 2

class WrongNumberOfPlayersError < StandardError ; end
class NoSuchStrategyError < StandardError; end

def rps_game_winner(game)
	raise WrongNumberOfPlayersError unless game.length == 2
	game.each{ |player| raise NoSuchStrategyError unless player[1].downcase =~ /^(r|p|s)$/}
	if (game[0][1] + game[1][1]).downcase =~ /^(sp|pr|rs|ss|pp|rr)$/ 
		return game[0]
	else 
		return game[1]
	end
end

# print rps_game_winner([ [ "Armando", "P" ], [ "Dave", "P" ] ])

def rps_tournament_winner(tournament)
  if tournament[0][1].is_a?(String)
    player_1 = tournament[0]
    player_2 = tournament[1]
  else
    player_1 = rps_tournament_winner(tournament[0])
    player_2 = rps_tournament_winner(tournament[1])
  end
  return rps_game_winner([player_1, player_2])
end

=begin
print rps_tournament_winner(
[
[
[ ["Armando", "P"], ["Dave", "S"] ],
[ ["Richard", "R"],  ["Michael", "S"] ],
],
[ 
[ ["Allen", "S"], ["Omer", "P"] ],
[ ["David E.", "R"], ["Richard X.", "P"] ]
]
]
)
=end

# PART 3

def combine_anagrams(words)
  words_hash = Hash.new{ |hash, key| hash[key] = [] }
  words.each { |word| word_key = word.downcase.chars.sort.join; words_hash[word_key] = words_hash[word_key] << word; }
  words_list = Array.new()
  words_hash.keys.each { |key| words_list << words_hash[key] }
  return words_list
end

# print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
print combine_anagrams(['C', 'c'])
print combine_anagrams(['C', 'C'])

# PART 4

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

class JellyBean < Dessert
  
  attr_accessor :flavor

  def initialize(name, calories, flavor)
    @flavor = flavor
  end

  def delicious?
    return @flavor.downcase !~ /black +licorice/
  end

end

=begin
dessert = Dessert.new("Pie",197)
#print dessert.healthy?
#print dessert.delicious?

jb = JellyBean.new("Black Licorice",100,"Black Licorice")
print jb.delicious?
=end

# PART 5

class Class
  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s
    attr_reader attr_name
    attr_reader attr_name+"_history"
    class_eval %Q{
      def #{attr_name}=(attr_name)
        @attr_name = attr_name
        if @#{attr_name}_history then
          @#{attr_name}_history << attr_name
        else
          @#{attr_name}_history = Array.new
          @#{attr_name}_history << nil
          @#{attr_name}_history << attr_name
        end
      end
    }
  end
end

class Foo
  attr_accessor_with_history :bar
end

=begin
f = Foo.new
f.bar = 1
f.bar = 2
print f.bar_history
=end
