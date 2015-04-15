class Robot
  NAME_LETTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
  NAME_NUMBERS = '0123456789'
  attr_accessor :name
  @@RobotNames = []
  def initialize
    @name = ""
    2.times {@name << NAME_LETTERS[rand(NAME_LETTERS.size)]} + 3.times {@name << NAME_NUMBERS[rand(NAME_NUMBERS.size)]}
  end
  
  def reset
    @name = ""
    2.times {@name << NAME_LETTERS[rand(NAME_LETTERS.size)]} + 3.times {@name << NAME_NUMBERS[rand(NAME_NUMBERS.size)]}
  end
  
end
