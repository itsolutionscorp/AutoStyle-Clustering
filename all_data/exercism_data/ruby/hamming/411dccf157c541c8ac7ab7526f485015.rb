class Hamming
  def self.compute(strand1, strand2)
    i = 0
    strand1.length.times do |num|
      unless strand1[num] == strand2[num]
          i = i + 1
      end
    end
    i
  end
end
