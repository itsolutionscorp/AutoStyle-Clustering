def compute(derp, flerp)
    derp.chars.zip(flerp.chars).select do |a,b|
      a != b
    end.count
  end