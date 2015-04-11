class Robot
  def name
    @name ||= generate(2, 'A'..'Z', 26) + generate(3, '0'..'9', 9)
  end

  def reset
    @name = nil
  end

  def generate(n, range, sample_size)
    n.times.collect { range.to_a.slice(rand sample_size) }.join
  end
end
