class Complement
  def self.of_dna(x)
    one_letter_array = x.split(//)
    transcription = one_letter_array.map do |string|
      case string
      when 'G'
        'C'
      when 'C'
        'G'
      when 'T'
        'A'
      when 'A'
        'U'
      end
    end
    transcription.join
  end

  def self.of_rna(x)
    one_letter_array = x.split(//)
    transcription = one_letter_array.map do |string|
      case string
      when 'G'
        'C'
      when 'C'
        'G'
      when 'A'
        'T'
      when 'U'
        'A'
      end
    end
    transcription.join
  end
end
