class Hamming
  def self.compute(strand_1, strand_2)
    total = 0
    strand_1.split('').each_with_index do |nucleobase, index|
      total += 1 if nucleobase != strand_2[index]
    end

    total
  end
end
