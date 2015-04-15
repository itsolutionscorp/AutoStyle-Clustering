class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    [letter, letter, number, number, number].join
  end

  def letter
    ('A'..'Z').to_a.shuffle.pop
  end

  def number
    (0..9).to_a.shuffle.pop.to_s
  end

end
