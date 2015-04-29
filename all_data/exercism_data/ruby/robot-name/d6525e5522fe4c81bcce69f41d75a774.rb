class Robot

  def name
    @name ||= generate_random_name
  end

  def reset
    @name = nil
  end

  private
  def generate_random_name
    [
      random_letter,
      random_letter,
      random_digit,
      random_digit,
      random_digit,
    ].join
  end

  def random_digit
    ('0'..'9').to_a.sample
  end

  def random_letter
    ("A".."Z").to_a.sample
  end
end
