class Complement
  def self.of_dna(sequence)
    sequence.chars.map do |letter|
      if letter == 'C'
        letter = 'G'
      elsif letter == 'G'
        letter = 'C'
      elsif letter == 'T'
        letter = 'A'
      elsif letter == 'A'
        letter = 'U'
      end
    end.join('')
  end

  def self.of_rna(sequence)
    sequence.chars.map do |letter|
      if letter == 'C'
        letter = 'G'
      elsif letter == 'G'
        letter = 'C'
      elsif letter == 'U'
        letter = 'A'
      elsif letter == 'A'
        letter = 'T'
      end
    end.join('')
  end
end
