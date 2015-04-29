class Robot
  attr_reader :name

  def initialize
    @name = random_name
  end

  def reset
    @name = random_name
  end

private

  def random_name
    (2.times.map { LETTERS.sample } +
      3.times.map { DIGITS.sample }).join
  end

  LETTERS = ('A'..'Z').to_a
  DIGITS  = (0..9).to_a
end
