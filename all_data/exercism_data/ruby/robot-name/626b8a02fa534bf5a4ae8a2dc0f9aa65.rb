require 'set'

class Robot

  attr_accessor :name
  @@names = Set.new

  def initialize
    create_name    
  end

  # Create a robot name in the format LLNNN
  # where L is a random letter and N is a random digit
  # There should be 26 * 26 * 10 * 10 * 10 = 676000 possibilities
  # If a lot of robots get created then this will slow down and eventually fail
  def create_name
    return "" if @@names.size == 676000

    length = @@names.size

    while @@names.size == length
      @name = "#{letter}#{letter}#{number}#{number}#{number}"
      @@names << @name
    end
  end

  def letter
    [*('A'..'Z')].sample
  end

  def number
    [*(0..9)].sample
  end

  def reset
    @@names.delete(@name)
    create_name
  end

  def Robot::names
    @@names
  end

end
