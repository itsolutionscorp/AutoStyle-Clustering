class Complement

  def self.of_dna(rna)
    dna_complements = { 'C' => 'G', 
                        'G' => 'C', 
                        'T' => 'A', 
                        'A' => 'U' }
    rna.each_char.map { |nucleotide| dna_complements[nucleotide] }.join("")
  end

  def self.of_rna(dna)
    rna_complements = { 'G' => 'C', 
                        'C' => 'G', 
                        'A' => 'T', 
                        'U' => 'A' }
    dna.each_char.map { |nucleotide| rna_complements[nucleotide] }.join("")
  end
end
