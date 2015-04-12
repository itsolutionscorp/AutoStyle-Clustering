def compute(a, b)
    # zip into array of pairs
    pairs = a.split(//).zip(b.split(//))
    # count non-identical pairs
    pairs.select { |(first, second)| first != second }.length
  end