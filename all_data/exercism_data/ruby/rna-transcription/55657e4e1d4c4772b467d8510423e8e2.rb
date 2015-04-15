class Complement

  def self.find_complement(strand, invert=false)
    dna_to_rna_table = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U' 
    }

    dna_to_rna_table = dna_to_rna_table.invert if invert

    complements = ""

    strand.chars.each do |nucleotide| 
      complements += dna_to_rna_table.fetch(nucleotide) { raise ArgumentError }
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
