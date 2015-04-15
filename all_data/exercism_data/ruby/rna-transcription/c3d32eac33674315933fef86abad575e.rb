class Complement

  def self.of_dna(rna_strand)
    index = 0
    dna = []
    while index < rna_strand.length
      if rna_strand[index] == 'G'
        dna[index] = 'C'
      elsif rna_strand[index] == 'C'
        dna[index] = 'G'
      elsif rna_strand[index] == 'T'
        dna[index] = 'A'
      elsif rna_strand[index] == 'A'
        dna[index] = 'U'
      end
      index += 1
    end
    dna.inject(:+)
  end

  def self.of_rna(dna_strand)
    index = 0
    rna = []
    while index < dna_strand.length
      if dna_strand[index] == 'C'
        rna[index] = 'G'
      elsif dna_strand[index] == 'G'
        rna[index] = 'C'
      elsif dna_strand[index] == 'A'
        rna[index] = 'T'
      elsif dna_strand[index] == 'U'
        rna[index] = 'A'
      end
      index += 1
    end
    rna.inject(:+)
  end

end
