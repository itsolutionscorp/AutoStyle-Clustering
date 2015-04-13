def compute(a, b)
    a.chars.zip(b.chars).count do |a, b|
      next if b.nil?
      a != b
    end
  end