class Hamming
  def compute(first, second)
    distance = first.chars.zip(second.chars).count do |a, b|
      a != b
    end
    distance
  end
end
