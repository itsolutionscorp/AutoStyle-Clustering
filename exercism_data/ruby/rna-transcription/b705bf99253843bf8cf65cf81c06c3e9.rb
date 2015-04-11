class Complement

  def self.of_dna(dna)
    array = dna.chars.collect do |char|
      case char
        when 'T' then 'A'
        when 'A' then 'U'
        when 'C' then 'G'
        when 'G' then 'C'
      end
    end
    return array.join
  end

  def self.of_rna(rna)
    array = rna.chars.collect do |char|
      case char
        when 'A' then 'T'
        when 'U' then 'A'
        when 'G' then 'C'
        when 'C' then 'G'
      end
    end
    return array.join
  end

end
