class Complement
  @@complement_hash = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna_strand)
    dna_strand.chars.map{ |nucleotide| @@complement_hash[nucleotide] }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map { |nucleotide| @@complement_hash.key(nucleotide) }.join
  end
end
