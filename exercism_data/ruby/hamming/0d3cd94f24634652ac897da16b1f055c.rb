module Hamming
  def self.compute(x, y)
    strand1, strand2 = x.chars, y.chars
    counter, diff = 0, 0
    length = x.size > y.size ? y.size : x.size

    until counter == length
      if strand1[counter] != strand2[counter]
        diff += 1
      end
      counter += 1
    end

    diff
  end
end
