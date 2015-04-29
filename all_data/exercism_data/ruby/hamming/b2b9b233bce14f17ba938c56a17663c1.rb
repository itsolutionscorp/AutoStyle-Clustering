module Hamming
	def self.compute(a, b)
    a, b = self.size_equally(a,b)
    a.unpack("C*")
      .zip(b.unpack("C*"))
      .map { |a,b| a ^ b }
      .reject { |c| c == 0 }
      .size
  end

  def self.size_equally(a, b)
    if a.size < b.size
      [a, b[0...a.size]]
    else
      [a[0...b.size], b]
    end
  end
end
