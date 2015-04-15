class Hamming
  def self.compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).reduce(0) do |sum, (x, y)|
      if x && y && x != y
      	sum + 1
      else
      	sum
      end
    end
  end
end
