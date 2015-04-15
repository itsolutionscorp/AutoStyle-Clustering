def compute(a,b)
    # Make both strings into arrays
    a = a.scan(/./)
    b = b.scan(/./)

    hamming_distance = 0

    a.zip(b).each do |base1, base2|
      break if base2.nil?
      hamming_distance += 1 if base1 != base2
    end

    return hamming_distance

  end