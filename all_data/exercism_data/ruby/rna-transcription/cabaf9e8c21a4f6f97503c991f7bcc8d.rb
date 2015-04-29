class Complement

  def self.of_dna(dna)
    translate = dna.chars.to_a
    translated = ""
    translate.each do |d|
      if d == 'G'
        translated += 'C'
      elsif d == 'C'
       translated += 'G'
      elsif d == 'T'
       translated += 'A'
      else
        translated += 'U'
      end
     end
     return translated
  end
  
  def self.of_rna(rna)
    translate = rna.chars.to_a
    translated = ""
    translate.each do |d|
      if d == 'C'
        translated += 'G'
      elsif d == 'G'
        translated += 'C'
      elsif d == 'A'
        translated += 'T'
      else
        translated += 'A'
      end
    end
    return translated
  end

end
