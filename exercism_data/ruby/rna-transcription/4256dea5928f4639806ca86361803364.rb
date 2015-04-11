class Complement


  def self.of_dna(nucleotide)
    final = ""
    nucleotide.each_char do |n|
      raise ArgumentError if n == 'U'
      final << switch_nucleotide(n, true)
    end
    final
  end

  def self.of_rna(nucleotide)
    final = ""
    nucleotide.each_char do |n|
      raise ArgumentError if n == 'T'
      final << switch_nucleotide(n, false)
    end
    final
  end

private
  def self.switch_nucleotide(character, rna) 
    case character
    when "G"
      "C"
    when "C"
      "G"
    when "A"
      if rna 
        "U"
      else
        "T"
      end
    when "T"
      "A"
    when "U"
      "A"
    end
  end

end
