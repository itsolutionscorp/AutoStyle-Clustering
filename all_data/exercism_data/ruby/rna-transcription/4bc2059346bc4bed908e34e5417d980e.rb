class Complement
  def self.of_dna(dna)
    rna = ""
    dna.chars.each do |base|
      case base
        when 'G' then rna << 'C'
        when 'C' then rna << 'G'
        when 'T' then rna << 'A'
        when 'A' then rna << 'U'
        when 'U' then raise ArgumentError
      end
    end
    rna
  end  
  
  def self.of_rna(rna)
    dna = ""
    rna.chars.each do |base|
      case base
        when 'C' then dna << 'G'
        when 'G' then dna << 'C'
        when 'A' then dna << 'T'
        when 'U' then dna << 'A'
        when 'T' then raise ArgumentError
      end
    end
    dna
  end  
end
