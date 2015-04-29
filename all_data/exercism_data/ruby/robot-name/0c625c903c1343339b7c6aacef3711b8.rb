LETTERS = [*"A".."Z"]
DIGITS = [*0..9].map(&:to_s)

class Robot
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
    ::LETTERS.shuffle.first
  end

  def random_number
    ::DIGITS.shuffle.first
  end

  attr_writer :name
end
