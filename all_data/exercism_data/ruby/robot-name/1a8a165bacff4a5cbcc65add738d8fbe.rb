#using class instance variable '@RobotNames' instead of class variable '@@RobotNames' for name uniqueness verification
class Robot
  NAME_LETTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
  NAME_NUMBERS = '0123456789'
  attr_accessor :name
  @RobotNames = []
  def initialize
    @name = getName
    Robot.addName(@name) #need to add a class method to access the class instance variable
  end
  
  def reset
    Robot.deleteName(@name)
    @name = getName
    Robot.addName(@name)
  end
  
  def self.names
    @RobotNames
  end
  
  def self.addName(name)
    @RobotNames << name
  end
  
  def self.deleteName(name)
    @RobotNames.delete(name)
  end
  
  def getName
    name = ""
    2.times {name << NAME_LETTERS[rand(NAME_LETTERS.size)]} + 3.times {name << NAME_NUMBERS[rand(NAME_NUMBERS.size)]}
    if Robot.names.include?(name)
      self.getName
    else
      return name
    end
  end
  
end
