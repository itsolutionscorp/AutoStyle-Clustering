def compute(a, b)

    pairs = [a.chars,b.chars].sort_by(&:count).reduce(&:zip)

    pairs.count {|character_a,character_b| character_a != character_b }
  end