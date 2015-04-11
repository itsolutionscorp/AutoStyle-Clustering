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
    "#{random_letter(2)}#{random_number(3)}"
  end

  def random_letter(number_of_characters)
    sequence = ''
    number_of_characters.times { sequence << CHARACTERS.sample }
    sequence
  end

  def random_number(number_of_digits)
    sequence = ''
    number_of_digits.times { sequence << DIGITS.sample }
    sequence
  end
end
