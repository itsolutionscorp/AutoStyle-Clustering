class Robot
  LETTERS=('A'..'Z').to_a
  DIGITS=('0'..'9').to_a

  def name
    @name ||= random_letters(2) + random_digits(3)
  end

  def reset
    @name = nil
  end

  private

  def random_letters(quantity) 
    random_objects(LETTERS, quantity).join
  end

  def random_digits(quantity)
    random_objects(DIGITS, quantity).join
  end

  def random_objects(object, quantity)
    0.upto(quantity).inject([]) { object.sample }
  end
end
