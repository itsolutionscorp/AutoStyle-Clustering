class Complement

  def self.of_dna(nucleotide)
    result = ""
    nucleotide.split("").each{ |element|
      result << translate_nucleotide(element)
    }.join()
    result
  end

  def self.of_rna(nucleotide)
    result = ""
    nucleotide.split("").each{ |element|
      result << translate_nucleotide(element)
    }.join()
    result.gsub(/[U]/,'T')
  end 


  def self.translate_nucleotide(element)
    case element
      when 'G'
        'C'
      when 'C'
        'G'
      when 'T'
        'A'
      when 'A'
        'U'
      when 'U'
        'A'
      else
        element
    end
  end
end
