class Complement

  def self.of_dna(dna)
    dna.split(//).map do |nuc|
      case (nuc)
        when 'G'
          'C'
        when 'C'
          'G'
        when 'T'
          'A'
        when 'A'
          'U'
      end
    end.join
  end

  def self.of_rna(rna)
    rna.split(//).map do |nuc|
      case (nuc)
        when 'G'
          'C'
        when 'C'
          'G'
        when 'U'
          'A'
        when 'A'
          'T'
      end
    end.join
  end

end
