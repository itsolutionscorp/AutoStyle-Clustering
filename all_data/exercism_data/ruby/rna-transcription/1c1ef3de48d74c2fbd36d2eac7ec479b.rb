class Complement
  def self.of_dna(dna)
    dna_array = dna.split("")
    rna_array = []
    dna_array.each do |nuc|  #nuc for nucleotide
      if nuc == 'G'
        rna_array << 'C'
      elsif nuc == 'C'
        rna_array << 'G'
      elsif nuc == 'T'
        rna_array << 'A'
      else
        rna_array << 'U'
      end 
    end

    return rna_array.join
  end


  def self.of_rna(rna)
    rna_array = rna.split("")
    dna_array = []
    rna_array.each do |nuc|  #nuc for nucleotide
      if nuc == 'C'
        dna_array << 'G'
      elsif nuc == 'G'
        dna_array << 'C'
      elsif nuc == 'A'
        dna_array << 'T'
      else
        dna_array << 'A'
      end 
    end

    return dna_array.join
  end
end
