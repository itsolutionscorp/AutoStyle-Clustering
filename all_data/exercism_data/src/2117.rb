def compute(a, b)
    a.chars.zip(b.chars).select { |a,b| a != b }.length
  end
end