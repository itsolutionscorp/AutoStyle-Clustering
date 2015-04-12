def compute(a,b)
    pairs = a.split('').zip b.split('')
    pairs.inject(0) {|distance, pair| distance += pair.uniq.size > 1 ? 1 : 0}
  end