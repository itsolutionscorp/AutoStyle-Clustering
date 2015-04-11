class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = RobotName.generate
  end
end

class RobotName
  def self.generate
    name = ""
    2.times { name << random_letter }
    3.times { name << random_digit }
    name
  end

  private

  def self.random_digit
    rand(0..9).to_s
  end

  def self.random_letter
    rand(97..122).chr
  end
end
