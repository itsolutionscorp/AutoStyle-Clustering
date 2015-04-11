class Robot
  DIGITS_NB = 3
  LETTERS_NB = 2
  ALPHABET = ('A'..'Z').to_a
  ALPHABET_SIZE = 26

  def name
    @name ||= letters.join + digits.join
  end

  def reset
    @name = nil
  end

  private

  def letters
    LETTERS_NB.times.map { ALPHABET[rand(ALPHABET_SIZE)] }
  end

  def digits
    DIGITS_NB.times.map { rand(10) }
  end
end
