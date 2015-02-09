def palindrome?(string)
  string = string.downcase.gsub(/[\W]/,"")
  string == string.reverse
end
def count_words(string)
  string.downcase!
  my_array = Hash.new(0)
  string.split(/[ ,-]+/).each do |word|
    my_array[word] = my_array[word] + 1
  end
  return my_array
end
class WrongNumberOfPlayersError < StandardError
end
class NoSuchStrategyError < StandardError
end
class Gambler
  attr_accessor  :player,:strategy
  def initialize()
    @player = "player1" 
    @strategy = "P" 
  end
end
class Mover
  include Comparable
  attr_accessor  :player,:strategy
  def initialize(a)
    @player = a[0] 
    @strategy = a[1] 
  end
  def my_array(a)
    @player = a[0] 
    @strategy = a[1] 
  end
  def <=> (other_mover)
    if ((self.strategy.downcase == 'p' &&  other_mover.strategy.downcase == 'r') \
        or \
      (self.strategy.downcase == 'r' &&  other_mover.strategy.downcase == 's') \
              or \
      (self.strategy.downcase == 's' &&  other_mover.strategy.downcase == 'p'))
      1
    elsif (self.strategy.downcase == other_mover.strategy.downcase)
      0
    else
      -1
    end
  end
  def inspect
    Array.new(self.player,self.strategy) 
  end
end
def rps_game_winner(game)
  raise WrongNumberOfPlayersError unless game.length == 2
  game.each do |player,strategy|
    raise NoSuchStrategyError unless strategy =~ /^[PRS]$/i
    puts "#{player} #{strategy}"
  end
  player1, strategy1, player2, strategy2 = game.flatten
  mover1 = Mover.new([player1,strategy1])
  mover2 = Mover.new([player2,strategy2])
  if mover1 > mover2
    return [player1,strategy1] 
  elsif mover2 > mover1
    return [player2,strategy2] 
  else
    return [player1,strategy1] 
  end
end
=begin
def rps_tournament_winner(bracketed_array)
  p bracketed_array
  puts "bracketed_array.length is #{bracketed_array.length}"
  puts "bracketed_array.flatten.length is #{bracketed_array.flatten.length}"
  if (bracketed_array.flatten.length == (2 ** 1) * 2)
    game = bracketed_array 
    winner = rps_game_winner(game)
    puts "The winner is #{winner}"
    return winner
  end
  if bracketed_array.flatten.length == (( 2 ** 2) * 2) 
    round11 = bracketed_array[0] 
    winner11 = rps_game_winner(round11)
    puts "The winner11 is #{winner11}"
    round12 = bracketed_array[1] 
    winner12 = rps_game_winner(round12)
    puts "The winner12 is #{winner12}"
    winner2 = rps_game_winner([winner11,winner12])
    puts "The winner2 is #{winner2}"
    return (winner2)
  end
  if bracketed_array.flatten.length == (( 2 ** 3) * 2) 

    round11 = bracketed_array[0][0] 
    winner11 = rps_game_winner(round11)
    puts "The winner11 is #{winner11}"

    round12 = bracketed_array[0][1] 
    winner12 = rps_game_winner(round12)
    puts "The winner12 is #{winner12}"

    round13 = bracketed_array[1][0] 
    winner13 = rps_game_winner(round13)
    puts "The winner13 is #{winner13}"

    round14 = bracketed_array[1][1] 
    winner14 = rps_game_winner(round14)
    puts "The winner14 is #{winner14}"

    round21 = [winner11, winner12]
    winner21 = rps_game_winner(round21)
    puts "The winner21 is #{winner21}"

    
    round22 = [winner13, winner14]
    winner22 = rps_game_winner(round22)
    puts "The winner22 is #{winner22}"

    winner3 = rps_game_winner([winner21,winner22])
    puts "The winner3 is #{winner3}"
    return (winner3)
  end
end
=end
def rps_tournament_winner(bracketed_array)
  p bracketed_array
  flatten_array = bracketed_array.flatten
  puts "bracketed_array.length is #{bracketed_array.length}"
  puts "flatten_array.length is #{flatten_array.length}"
  new_array = []
  flatten_array.each_index do |index|
    if ((index % 4) == 0)
      new_array << rps_game_winner( [[flatten_array[index], \
          flatten_array[index+1]] , [flatten_array[index+2], \
          flatten_array[index+3]]]).flatten 
    else
      next
    end
  end
  if (new_array.flatten.size > 2)
    rps_tournament_winner(new_array)
  else
    return(new_array.flatten)
  end
end
def combine_anagrams(input)
  p input
  answer = []
  my_hash = Hash.new
  input.each do |word|
    p word
    downcase_word = word.downcase
    temp_array =[]
    downcase_word.each_char do |ch|
      temp_array << ch 
    end
    sorted_array = temp_array.sort
    puts "word is #{word}"
    sorted_string = sorted_array.join("")
    puts "sorted_string is #{sorted_string}"
    p my_hash
    my_hash[sorted_string] ||= []
    my_hash[sorted_string] = my_hash[sorted_string].push(word) 
    p my_hash
  end
  my_hash.each_pair do |key, value|
    puts "key is #{key}"
    puts "value is #{value}"
    answer << value
  end
  return answer 
end
