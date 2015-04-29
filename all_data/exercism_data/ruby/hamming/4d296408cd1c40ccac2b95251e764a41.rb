class Hamming
  def self.compute(strand1, strand2)
    distance = 0
    strand1.split('').each_with_index do |n, i|
      distance += 1 if strand1[i] != strand2[i]
    end

    distance
  end
end
