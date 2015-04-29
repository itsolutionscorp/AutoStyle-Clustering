class Hamming
  def self.compute(dna1, dna2)
    nucleobases1 = dna1.chars
    nucleobases2 = dna2.chars

    paired_nucleobases = nucleobases1.zip(nucleobases2)

    paired_nucleobases.count do |(nucleobase1, nucleobase2)|
      nucleobase1 != nucleobase2
    end
  end
end
