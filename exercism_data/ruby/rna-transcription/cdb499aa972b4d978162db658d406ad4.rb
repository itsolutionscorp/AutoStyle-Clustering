class Complement
  def self.of_dna(dna)
    medical = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    rna = String.new
    dna.each_char { |c| rna << medical[c] }
    rna
  end

  def self.of_rna(rna)
    medical = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
    dna = String.new
    rna.each_char { |c| dna << medical[c] }
    dna
  end
end
