class Hamming

  def self.compute(dna1, dna2)
    hamming_distance = 0
    strand1, strand2 = [], []

    if dna1.length > dna2.length
      dna_tmp = dna1
      dna1 = dna2
      dna2 = dna_tmp
    end

    dna1.each_char { |char| strand1 << char }
    dna2.each_char { |char| strand2 << char }

    strand1.each_index do |i|
      hamming_distance +=1 if strand2[i] != strand1[i]
    end
    hamming_distance
  end
end
