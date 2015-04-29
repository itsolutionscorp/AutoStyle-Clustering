class Complement

  @@dna_to_rna_table= {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U' 
  }

  def self.find_complement(strand, invert=false)
    table = invert ? @@dna_to_rna_table.invert : @@dna_to_rna_table
    complements = ""

    strand.chars.each do |nucleotide| 
      complements += table.fetch(nucleotide) { raise ArgumentError }
    end

    complements
  end
    
  def self.of_dna(strand)
    find_complement(strand) 
  end

  def self.of_rna(strand)
    find_complement(strand, true) 
  end

end
