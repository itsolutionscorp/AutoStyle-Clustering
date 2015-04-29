class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna(strand)
    strand.split('').map do |nucleotide|
      DNA_COMPLEMENTS[nucleotide]
    end.join
  end

  def self.of_rna(strand)
    strand.split('').map do |nucleotide|
      RNA_COMPLEMENTS[nucleotide]
    end.join
  end
end
