class Complement

  def self.of_dna(entry)
    rna = entry.chars.map do |letter|
      dna_transcription(letter)
    end
    rna.join
  end

  def self.dna_transcription(entry)

    case entry
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    when 'A'
      'U'
    else
      puts "Hey invalid entry."
    end
  end

  def self.of_rna(entry)
    dna = entry.chars.map do |letter|
      rna_transcription(letter)
    end
    dna.join
  end


  def self.rna_transcription(letter)
    case letter
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    when 'A'
      'T'
    when 'U'
    'A'
    else
      puts "Hey invalid entry."
    end
  end
end
