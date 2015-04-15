def compute(a, b)
    a.chars.zip(b.chars).count do |acid1, acid2|
      acid2 && acid1 != acid2
    end
  end