class Robot

  def name
    @name ||= prefix + suffix
  end

  def prefix
    letters.sample(2).join
  end

  def suffix
    numbers.sample(3).join
  end

  def reset
    @name = nil
  end

private
  def letters
    ('a'..'z').to_a
  end

  def numbers
    (0..9).to_a
  end


end
