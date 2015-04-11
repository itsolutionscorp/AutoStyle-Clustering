class Hamming
  def self.compute strand1, strand2
    strand1 = strand1.split('')
    strand2 = strand2.split('')
    count = 0
    strand1.each_with_index do |single1, index|
      unless single1 == strand2[index] then count = count + 1 end
    end
    count
  end
end
