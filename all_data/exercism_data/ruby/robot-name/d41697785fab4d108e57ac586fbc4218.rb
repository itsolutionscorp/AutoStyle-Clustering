class Robot
  attr_accessor :name

  CHARACTERS = ('A'..'Z').to_a
  DIGITS = ('0'..'9').to_a

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    "#{random_letter_sequence(2)}#{random_number_sequence(3)}"
  end

  def random_letter_sequence(number_of_characters)
    sequence = ''
    number_of_characters.times { sequence << CHARACTERS.sample }
    sequence
  end

  def random_number_sequence(number_of_digits)
    sequence = ''
    number_of_digits.times { sequence << DIGITS.sample }
    sequence
  end
end
