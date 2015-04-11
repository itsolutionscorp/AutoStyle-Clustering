class Hamming
  def self.compute(strand1, strand2)
    return 0 if strand1 == strand2
    dist = 0
    strand1.split('').each_with_index do |n, i|
      dist += (n <=> strand2.split('')[i]).abs
    end
    dist
  end
end
