def compute(xs, ys)
    xs.split('')
      .zip(ys.split '')
      .select { |x, y| x != y }
      .size
  end