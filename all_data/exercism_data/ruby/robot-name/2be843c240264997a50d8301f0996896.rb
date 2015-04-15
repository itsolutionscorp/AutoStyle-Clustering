class Robot
  attr_reader :name

  LETTERS = [*'a'..'z']

  NUMBERS = [*0..9]

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  def generate_name
    @name = (Array.new(2){LETTERS.sample} + Array.new(3){NUMBERS.sample}).join
  end
end
