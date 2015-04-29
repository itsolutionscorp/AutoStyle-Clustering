class Hamming
  def self.compute(a,b)
    # Which strand is the shortest?
    short = a.length <= b.length ? a : b
    long = short == a ? b : a

    # Make both strings into arrays
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
end
