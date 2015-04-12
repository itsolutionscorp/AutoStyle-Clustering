class Hamming
  class << self
    def compute(strand1, strand2)
      distance = 0

      return distance if strand1 == strand2

      aminoacids1 = strand1.split('')
      aminoacids2 = strand2.split('')

      aminoacids1.each do |a1|
        distance += 1 if a1 != aminoacids2.shift
        break if aminoacids2.size == 0
      end

      distance
    end
  end
end
