class Robot
  LETTERS = [*"A".."Z"]
  DIGITS = [*"0".."9"]

  def name
    @name ||= generate_name
  end

  def reset
    self.name = nil
  end

  private

  def generate_name
    "#{random_letters 2 }#{random_numbers 3 }"
  end

  def random_letters number
    (LETTERS * number).sample(number).join
  end

  def random_numbers number
    (DIGITS * number).sample(number).join
  end

  attr_writer :name
end
