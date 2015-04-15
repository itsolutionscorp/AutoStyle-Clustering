module Hamming
  def self.compute(a, b)
    a, b = self.size_equally(a,b)
    # Convert every char to the ASCII byte value
    a.unpack("C*").
      # And make an array of byte-pairs for each position of a[i], b[i]
      zip(b.unpack("C*")).
      # Then count up each pair whose XOR is true
      reduce(0) { |memo, (a, b)| memo += 1 unless a ^ b == 0; memo }
  end

  def self.size_equally(a, b)
    if a.size < b.size
      [a, b[0...a.size]]
    else
      [a[0...b.size], b]
    end
  end
end
