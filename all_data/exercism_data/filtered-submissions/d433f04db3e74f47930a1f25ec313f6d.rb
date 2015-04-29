def compute(a, b)

    pairs = a.split(//).zip(b.split(//))

    pairs.select { |(first, second)| first != second }.length
  end