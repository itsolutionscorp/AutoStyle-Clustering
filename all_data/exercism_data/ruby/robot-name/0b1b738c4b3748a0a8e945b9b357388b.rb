class Robot
  @@letters = ('A'..'Z').to_a
  @@numbers = ('0'..'9').to_a
  
  attr_reader :name
  
  def initialize
    reset
  end
  
  def reset
    @name = (@@letters.sample(2) + @@numbers.sample(3)).join
  end
end
