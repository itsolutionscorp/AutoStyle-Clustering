class Complement

  def self.of_dna(bits)
    return_me = ''
    bits.each_char do |c|
      return_me += Complement.dna_to_rna c
    end
    return_me
  end

  def self.of_rna(bits)
    return_me = ''
    bits.each_char do |c|
      return_me += Complement.rna_to_dna c
    end
    return_me
  end

  def self.rna_to_dna(bit)
    return_me = ' '
    case bit
      when 'G' 
        return_me = 'C'
      when 'C' 
        return_me = 'G'
      when 'U' 
        return_me = 'A'
      when 'A' 
        return_me = 'T'
      else
        raise ArgumentError
    end        
    return_me
  end

  def self.dna_to_rna(bit)
    return_me = ' '
    case bit
      when 'G' 
        return_me = 'C'
      when 'C' 
        return_me = 'G'
      when 'T' 
        return_me = 'A'
      when 'A' 
        return_me = 'U'
      else
        raise ArgumentError
    end        
    return_me
  end


end
