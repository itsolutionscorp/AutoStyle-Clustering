class Complement
  def self.of_dna(dna)
    dna.chars.collect {|x|
      case x
      when 'G'
        'C'
      when 'C'
        'G'
      when 'T'
        'A'
      when 'A'
        'U'
      else
        x
      end
    }.join
  end

  def self.of_rna(rna)
    rna.chars.collect {|x|
      case x
      when 'G'
        'C'
      when 'C'
        'G'
      when 'A'
        'T'
      when 'U'
        'A'
      else
        x
      end
    }.join
  end
end
