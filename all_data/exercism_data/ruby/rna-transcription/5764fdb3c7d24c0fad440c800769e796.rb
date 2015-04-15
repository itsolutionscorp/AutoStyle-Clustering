class Complement
  def self.of_dna(new_strand)
    return_String = String.new
    new_strand.split(//).each {|char|
      case 
      when (char == 'G')
        return_String += 'C'
      when (char == 'C')
        return_String += 'G'
      when (char == 'T')
        return_String += 'A'
      when (char == 'A')
        return_String += 'U'
      end
    }
    return return_String
  end

  def self.of_rna(new_strand)
    return_String = String.new
    new_strand.split(//).each {|char|
      case
      when (char == 'G')
        return_String += 'C'
      when (char == 'C')
        return_String += 'G'
      when (char == 'U')
        return_String += 'A'
      when (char == 'A')
        return_String += 'T'
      end
     }
    return return_String
  end
end
