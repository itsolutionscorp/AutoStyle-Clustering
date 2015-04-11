class Complement

  def self.of_dna(str)

    dna = ''

    str.size.times do |i|
      if str[i] == 'G'
        dna.concat('C')
      elsif str[i] == 'C'
        dna.concat('G')
      elsif str[i] == 'T'
        dna.concat('A')
      else str[i] == 'A'
        dna.concat('U')
      end
    end
    return dna
  end

  def self.of_rna(str)

    dna = ''

    str.size.times do |i|
      if str[i] == 'C'
        dna.concat('G')
      elsif str[i] == 'G'
        dna.concat('C')
      elsif str[i] == 'A'
        dna.concat('T')
      else str[i] == 'U'
        dna.concat('A')
      end
    end
    return dna
  end

end
