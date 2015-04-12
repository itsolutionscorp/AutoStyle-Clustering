class Hamming

  class << self
    def compute(strand_1, strand_2)
      total = 0
      min_length = strand_1.length > strand_2.length ? strand_2.length : strand_1.length
      (0...min_length).each do |index|
        total += 1 if strand_1[index] != strand_2[index]
      end
      total
    end

  end
end
