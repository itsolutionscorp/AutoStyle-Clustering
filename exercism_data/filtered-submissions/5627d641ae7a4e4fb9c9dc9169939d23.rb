def compute(one, other)
    one_bases = one.chars
    other_bases = other.chars

    zipped = one_bases.zip(other_bases)

    distance = 0

    zipped.each do |pair|
      next if pair.include?(nil)
      distance += 1 if pair.first != pair.last
    end

    distance
  end