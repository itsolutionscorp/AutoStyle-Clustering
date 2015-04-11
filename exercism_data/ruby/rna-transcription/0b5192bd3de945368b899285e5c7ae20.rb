class Complement
  def self.of_dna(strand)
    rna = ""
    strand.each_char { |c| rna += transcript_to_rna(c) }
    rna
  end
  
  def self.of_rna(strand)
    dna = ""
    strand.each_char { |c| dna += transcript_to_dna(c) }
    dna
  end
  
  private
  def self.transcript_to_rna(nucleotide)
    case nucleotide
      when "A" then "U"
      when "G" then "C"
      when "C" then "G"
      when "T" then "A"
    end
  end
  
  def self.transcript_to_dna(nucleotide)
    case nucleotide
      when "U" then "A"
      when "C" then "G"
      when "G" then "C"
      when "A" then "T"
    end
  end
end

