class Robot
  def name
    @name ||= generate_letters + generate_numbers
  end

  def reset
    @name = nil
  end

  def generate_letters
    ('AA'..'ZZ').to_a.sample
  end

  def generate_numbers
    (0..9).to_a.sample(3).join
  end
end
