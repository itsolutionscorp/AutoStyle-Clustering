def compute(a, b)
    a.chars.zip(b.chars).count do |x, y|
       x != y
    end
  end