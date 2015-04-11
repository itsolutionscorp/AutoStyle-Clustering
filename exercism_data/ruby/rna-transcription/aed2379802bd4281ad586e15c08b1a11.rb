class Complement
  def self.of_dna(dna)
    rna = ''
    (dna.scan /\w/).each do |i|
      case i.upcase
      when 'G'
        rna << 'C'
      when 'C'
        rna << 'G'
      when 'T'
        rna << 'A'
      when 'A'
        rna << 'U'
      end
    end
    rna   
  end

  def self.of_rna(rna)
    dna = ''
    (rna.scan /\w/).each do |i|
      case i.upcase
      when 'C'
        dna << 'G'
      when 'G'
        dna << 'C'
      when 'A'
        dna << 'T'
      when 'U'
        dna << 'A'
      end
    end
    dna   
  end
end
