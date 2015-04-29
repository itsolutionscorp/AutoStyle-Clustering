module Nucleotides
  Adenosine = 'A'
  Cytidine  = 'C'
  Guanosine = 'G'
  Thymidine = 'T'
  Uridine   = 'U'  
  
  DNA = [Adenosine, Cytidine, Guanosine, Thymidine]
  RNA = [Adenosine, Cytidine, Guanosine, Uridine]
end

class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def nucleotide_counts
    Nucleotides::DNA.each_with_object({}) do |nucleotide_code, result|
      result[nucleotide_code] = count(nucleotide_code)
    end
  end

  def count(nucleotide_code)
    unless Nucleotides::DNA.include?(nucleotide_code) || Nucleotides::RNA.include?(nucleotide_code)
      raise ArgumentError
    end
    @sequence.chars.select{|c| c == nucleotide_code }.count
  end
end
