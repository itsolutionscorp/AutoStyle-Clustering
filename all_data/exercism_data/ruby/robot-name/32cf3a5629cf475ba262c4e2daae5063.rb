class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

private

  def generate_name
    random_letters << random_digits
  end

  def random_letters
    ('A'..'Z').to_a.sample(2).join
  end

  def random_digits
    rand(999).to_s.ljust(3, '0')
  end

end
