class Robot
  DIGITS_NB = 3
  LETTERS_NB = 2
  ALPHABET = ('A'..'Z').to_a
  ALPHABET_SIZE = 26

  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = letters.join + digits.join
  end

  private

  def letters
    LETTERS_NB.times.map { ALPHABET[Random.rand(1..ALPHABET_SIZE)-1] }
  end

  def digits
    DIGITS_NB.times.map { Random.rand(0..9) }
  end
end
