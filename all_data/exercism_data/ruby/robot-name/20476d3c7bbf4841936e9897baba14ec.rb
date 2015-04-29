class Robot
  attr_reader :name
  
  def initialize
    @name = random_string
  end

  def reset
    @name = random_string
  end

  def random_string
    letters = [('a'..'z'), ('A'..'Z')].map { |i| i.to_a }.flatten
    numbers = [(0..9)].map { |i| i.to_a }.flatten

     return (0..1).map { letters[rand(letters.length)] }.join + 
     (0..2).map { numbers[rand(numbers.length)] }.join
  end
end
