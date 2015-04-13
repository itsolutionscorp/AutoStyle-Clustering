def compute(strand1, strand2)

    base1 = strand1.split(//)
    base2 = strand2.split(//)

    distance = 0

    base1.count.times do |b|

      if base1.count == base2.count && base1[b] != base2[b]
        distance += 1
      end
    end

    return distance
  end