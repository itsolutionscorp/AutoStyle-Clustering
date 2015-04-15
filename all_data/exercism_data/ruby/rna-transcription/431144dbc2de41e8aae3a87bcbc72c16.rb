class Complement 
  def self.of_dna(dna_string) 
    rna_array = Array.new
    (0..(dna_string.size - 1)).each do |x| 
      case dna_string[x] 
      when 'C'
        rna_array << 'G'
      when 'G'
        rna_array << 'C' 
      when 'T' 
        rna_array << 'A'
      when 'A' 
        rna_array << 'U'
      end 
    end
    return rna_array.join
  end

  def self.of_rna(rna_string) 
    dna_array = Array.new
    (0..(rna_string.size - 1)).each do |x| 
      case rna_string[x] 
      when 'C'
        dna_array << 'G'
      when 'G'
        dna_array << 'C' 
      when 'U' 
        dna_array << 'A'
      when 'A' 
        dna_array << 'T'
      end 
    end
    return dna_array.join
  end
end
