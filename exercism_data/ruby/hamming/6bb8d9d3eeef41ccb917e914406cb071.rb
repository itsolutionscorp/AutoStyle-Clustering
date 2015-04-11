module Hamming
  def self.compute strand1, strand2
    strand1.split(//).each_with_index.inject(0) do |sum, (letter, index)|
      sum + (letter == strand2[index] || !strand2[index] ? 0 : 1)
    end
  end
end
