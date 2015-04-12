class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).map { |pair|
      if (pair[0] == pair[1]) || pair.include?(nil) then 0 else 1 end
    }.inject(0, :+)
  end
end
