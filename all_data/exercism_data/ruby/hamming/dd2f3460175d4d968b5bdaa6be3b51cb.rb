class Hamming
  def self.compute(strand1, strand2)
    if strand1.size != strand2.size
      raise "Can't compute hamming distance for strands of unequal length"
    end

    strand1.upcase!
    strand2.upcase!
    distance = 0
    strand1.size.times do |index|
      unless strand1[index].eql? strand2[index]
        distance += 1
      end
    end
    distance
  end
end
