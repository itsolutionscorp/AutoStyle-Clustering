class Robot

  def name
    @name ||= random_name
  end

  def reset
    @name = nil
  end

private

  def random_name
    random_prefix + random_suffix
  end

  def random_prefix
    Array('A'..'Z').sample(2).join
  end

  def random_suffix
    Array(0..9).sample(3).join
  end

end
