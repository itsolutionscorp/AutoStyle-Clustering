def compute(a, b)
    a.chars.zip(b.chars).reject { |l,r| (l && r).nil? || l == r }.length
  end