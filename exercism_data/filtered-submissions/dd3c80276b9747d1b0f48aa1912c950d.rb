def compute( a, b)
    count = 0
    pairs = a.chars.zip(b.chars)
    pairs.take(b.length).count do | a, b|
      a != b
    end
  end