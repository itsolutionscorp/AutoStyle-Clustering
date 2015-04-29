module RobotNameGenerator
  WORD_CHARACTERS = [*'a'..'z', *'A'..'Z', '_']
  DIGITS = [*'0'..'9']
  private_constant :WORD_CHARACTERS, :DIGITS

  def self.generate_name
    (WORD_CHARACTERS.sample(2) + DIGITS.sample(3)).join
  end
end

class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = RobotNameGenerator.generate_name
  end
end
