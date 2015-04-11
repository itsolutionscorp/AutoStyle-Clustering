class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    "#{alpha_portion}#{numeric_portion}"
  end

  ALPHA_LENGTH = 2
  NUMERIC_LENGTH = 3

  def alpha_portion
    ALPHA_LENGTH.times.collect { capital_letters.sample }.join("")
  end

  def numeric_portion
    NUMERIC_LENGTH.times.collect { digits.sample }.join("")
  end

  def capital_letters
    ('A'..'Z').to_a
  end

  def digits
    ('0'..'9').to_a
  end

end
