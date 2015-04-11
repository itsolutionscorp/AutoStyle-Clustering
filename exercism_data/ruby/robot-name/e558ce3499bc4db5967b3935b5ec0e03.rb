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
    "#{random_letter * 2}#{random_number * 3}"
  end

  def random_letter
    LETTERS.sample
  end

  def random_number
    DIGITS.sample
  end

  attr_writer :name
end
