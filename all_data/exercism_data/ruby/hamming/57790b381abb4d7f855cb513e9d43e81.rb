class Hamming
  def self.compute(strand0, strand1)
    return 0 if strand0 == strand1
    i = 0
    distance = 0
    strand0.each_char do |char|
      break if strand1[i].nil?
      if char != strand1[i]
        distance += 1
      end
      i += 1
    end
    distance
  end
end
