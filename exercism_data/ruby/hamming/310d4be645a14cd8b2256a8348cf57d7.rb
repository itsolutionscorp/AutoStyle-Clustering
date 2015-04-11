class Hamming
  def self.compute(a,b)
    # if a is longer than b swap them as we are to only compare up to the length of the shorter input.
    if (a.length > b.length)
      a,b = b,a
    end

    distance = 0
    0.upto(a.length-1) do |i|
      distance += 1 if a[i] != b[i]
    end

    return distance
  end
end
