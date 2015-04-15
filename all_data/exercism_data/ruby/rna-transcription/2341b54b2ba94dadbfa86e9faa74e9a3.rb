class Complement

  def self.of_dna(bits)
    return_me = ''
    index = 0
    while index < bits.size
      return_me += Complement.nucleotide bits[index]
      index += 1
    end    
    raise ArgumentError if return_me.include?(' ')
    return_me
  end

  def self.of_rna(bits)
    return_me = ''
    index = 0
    while index < bits.size
      return_me += Complement.dns_strand bits[index]
      index += 1
    end    
    raise ArgumentError if return_me.include?(' ')
    return_me
  end

  def self.dns_strand(bit)
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
    end        
    return_me
  end

  def self.nucleotide(bit)
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
    end        
    return_me
  end


end
