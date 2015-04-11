class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = name_generator.generate
  end

private

  def name_generator
    @generator ||= NameGenerator.new
  end
end

class NameGenerator

  def generate
    (2.times.map { LETTERS.sample } +
      3.times.map { DIGITS.sample }).join
  end

  LETTERS = ('A'..'Z').to_a
  DIGITS  = (0..9).to_a
end
