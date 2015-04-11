class Complement

  def self.of_dna(dna_string)
    rna = ''
    dna_string.each_char do |char|
      if char == 'G'
        rna << 'C'
      elsif char == 'C'
        rna << 'G'
      elsif char == 'T'
        rna << 'A'
      elsif char == 'A'
        rna << 'U'
      end  
    end
    rna
  end

  def self.of_rna(rna_string)
    dna = ''
    rna_string.each_char do |char|
      if char == 'G'
        dna << 'C'
      elsif char == 'C'
        dna << 'G'
      elsif char == 'U'
        dna << 'A'
      elsif char == 'A'
        dna << 'T'
      end  
    end
    dna
  end

end
