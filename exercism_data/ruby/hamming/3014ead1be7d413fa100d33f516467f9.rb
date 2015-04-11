class Hamming
  def self.compute(strand_1, strand_2)
    strand_1 = strand_1.chars
    strand_2 = strand_2.chars

    min = (strand_1.length < strand_2.length) ? strand_1.length : strand_2.length

    strand_1 = strand_1.take(min)
    strand_2 = strand_2.take(min)

    hamm = 0

    strand_1.each_index do |index|
      hamm += 1 if strand_1[index] != strand_2[index]
    end
    hamm
  end
end
