def compute(a,b)

    short = a.length <= b.length ? a : b
    long = short == a ? b : a


    short = short.scan(/./)
    long = long.scan(/./)

    hamming_distance = 0

    short.zip(long).each do |base1, base2|
      if base1 != base2
        hamming_distance += 1
      end
    end

    return hamming_distance

  end