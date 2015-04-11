class Complement

  DNA_COMPLEMENT = {
  'C' => 'G', 
  'G' => 'C', 
  'T' => 'A', 
  'A' => 'U'
  }
  RNA_COMPLEMENT = {
  'G' => 'C',
  'C' => 'G',
  'A' => 'T',
  'U' => 'A'
  }

  def self.of_dna(dna_nucleotide)
    dna_nucleotide.chars.map{|nucleotide| DNA_COMPLEMENT[nucleotide]}.join('')
  end

  def self.of_rna(rna_nucleotide)
    rna_nucleotide.chars.map{|nucleotide| RNA_COMPLEMENT[nucleotide]}.join('')
  end

end
