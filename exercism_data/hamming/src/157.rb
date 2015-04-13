def compute(*args)
    pairs_a, pairs_b = args.sort_by(&:length).map(&:chars)
    pairs_a.zip(pairs_b).find_all {|(a, b)| a != b }.length
  end