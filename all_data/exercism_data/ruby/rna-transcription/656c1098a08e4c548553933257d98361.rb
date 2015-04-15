class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna_strand)
    dna_strand.chars.map { |c| DNA_TO_RNA[c] }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map { |c| DNA_TO_RNA.key(c) }.join
  end

end
