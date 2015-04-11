class Complement

  def self.of_dna(dna)
    dna.each_char.each_with_object('') do |c, complement|
      case c
      when 'C'
        complement << 'G'
      when 'G'
        complement << 'C'
      when 'T'
        complement << 'A'
      when 'A'
        complement << 'U'
      end
    end
  end

  def self.of_rna(rna)
    rna.each_char.each_with_object('') do |c, complement|
      case c
      when 'C'
        complement << 'G'
      when 'G'
        complement << 'C'
      when 'U'
        complement << 'A'
      when 'A'
        complement << 'T'
      end
    end
  end
end
