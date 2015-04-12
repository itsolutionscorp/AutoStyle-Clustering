class Hamming

  def compute(a, b)
    if a == b
      return 0
    elsif a != b
      count = 0
      a.chars.zip(b.chars).each do |x, y|
        if x != y
          count += 1
        end
      end
      count
    end
  end
end
