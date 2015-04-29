class Robot
  LETTERS = [*"A".."Z"]
  DIGITS = [*0..9].map(&:to_s)

  def name
    @name ||= generate_name
  end

  def reset
    self.name = generate_name
  end

  private

  def generate_name
    "#{random_letters(2)}#{random_numbers(3)}"
  end

  def random_letters(number)
    LETTERS.sample(number).join
  end

  def random_numbers(number)
    DIGITS.sample(number).join
  end

  attr_writer :name
end
