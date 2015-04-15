class Complement

  def self.of_dna(dna)
    dna.split('').map do |e|
      case 
      when e == 'A'
        'U'
      when e == 'C'
        'G'
      when e == 'G'
        'C'
      when e == 'T'
        'A'
      else
        raise ArgumentError, 'Error in DNA sequence', caller
      end
    end.join
  end

  def self.of_rna(rna)
    rna.split('').map do |e|
      case 
      when e == 'U'
        'A'
      when e == 'G'
        'C'
      when e == 'C'
        'G'
      when e == 'A'
        'T'
      else
        raise ArgumentError, 'Error in RNA sequence', caller
      end
    end.join
  end

end
