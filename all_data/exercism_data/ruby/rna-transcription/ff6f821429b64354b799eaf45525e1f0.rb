class Complement
  DNA_COMPLEMENTS = {
    G: 'C',
    C: 'G',
    T: 'A',
    A: 'U',
    U: 'A',
  }

  RNA_COMPLEMENTS = {
    G: 'C',
    C: 'G',
    T: 'A',
    A: 'T',
    U: 'A'
  }

  def self.of_rna(acid_strand)
    complement(acid_strand, RNA_COMPLEMENTS)
  end

  def self.of_dna(acid_strand)
    complement(acid_strand, DNA_COMPLEMENTS)
  end

  def self.complement(acid_strand, mapping)
    acid_strand.split('').map do |acid|
      mapping[acid.to_sym]
    end.join('')
  end
end
