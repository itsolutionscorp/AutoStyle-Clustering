class Robot

  def name
    @name ||= random_name
  end

  def reset
    @name = nil
  end

  private

  def random_name
    "#{code}#{number}"
  end

  def code
    (0..1).map { choose_alphabetic }.join
  end

  def number
    (0..2).map { choose_digit }.join
  end

  def choose_alphabetic
    ('A'..'Z').to_a[rand(26)]
  end

  def choose_digit
    (0..9).to_a[rand(10)]
  end
end
