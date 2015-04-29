class Complement
  def self.of_dna(dna_strand)
    dna = dna_strand.split(//)
    rna = []
    dna_complements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
    dna.each do |nucleotide|
        rna.push(dna_complements[nucleotide])
    end
    rna.join
  end
  def self.of_rna(rna_strand)
    rna = rna_strand.split(//)
    dna = []
    rna_complements = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
    rna.each do |nucleotide|
      dna.push(rna_complements[nucleotide])
    end
    dna.join
  end
end
