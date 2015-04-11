class Robot
  NAME_LETTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
  NAME_NUMBERS = '0123456789'
  attr_accessor :name
  @@RobotNames = []
  def initialize
    @name = getName
    @@RobotNames << @name
  end
  
  def reset
    @@RobotNames.delete(@name)
    @name = getName
    @@RobotNames << @name
  end
  
  def self.nameLibrary
    @@RobotNames
  end
  
  def getName
    name = ""
    2.times {name << NAME_LETTERS[rand(NAME_LETTERS.size)]} + 3.times {name << NAME_NUMBERS[rand(NAME_NUMBERS.size)]}
    if Robot.nameLibrary.include?(name)
      self.getName
    else
      return name
    end
  end
  
end
