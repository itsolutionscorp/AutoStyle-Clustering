class Complement
  @@dna_rna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  def self.of_dna(dna_strand)
    dna_strand_arr = dna_strand.split('')
    dna_strand_arr.map {|dna| @@dna_rna[dna]}.join('')
  end
  def self.of_rna(rna_strand)
    rna_strand_arr = rna_strand.split('')
    rna_strand_arr.map {|rna| @@dna_rna.key(rna)}.join('')
  end
end
