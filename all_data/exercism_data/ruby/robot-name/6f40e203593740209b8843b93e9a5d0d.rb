class Robot
  ALPHABET = ('A'..'Z').to_a

  attr_reader :name

  def initialize(generator: Random.new)
    @generator = generator
    reset
  end

  def reset
    @name = random_letters(2) + random_digits(3)
  end

  private

  attr_reader :generator

  def random_letters(length)
    sequence_of(length) { random_letter }
  end

  def random_letter
    ALPHABET[generator.rand(ALPHABET.size)]
  end

  def random_digits(length)
     sequence_of(length) { random_digit }
  end

  def random_digit
    generator.rand(0..9)
  end

  def sequence_of(length, &block)
    length.times.map(&block).join
  end
end
