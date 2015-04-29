class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    [letters, numbers].join
  end

  def letters
    ('A'..'Z').to_a.sample(2)
  end

  def numbers
    (100..999).to_a.sample
  end

end
