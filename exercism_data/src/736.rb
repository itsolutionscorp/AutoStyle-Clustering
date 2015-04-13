def compute(a, b)
    pairs = [a.chars.to_a,b.chars.to_a].sort_by(&:length).reduce(&:zip)

    pairs.count {|pair_a,pair_b| pair_a != pair_b }
  end