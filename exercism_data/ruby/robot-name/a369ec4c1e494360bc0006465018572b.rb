class Robot
  attr_reader :name
  def initialize
    reset
  end
  def reset
    @name = Robot.reset
  end
  def self.reset
    characters = [*('A'..'Z'), *('a'..'z')].sample(2).join
    numbers = rand(100..999).to_s
    characters + numbers
  end
end
