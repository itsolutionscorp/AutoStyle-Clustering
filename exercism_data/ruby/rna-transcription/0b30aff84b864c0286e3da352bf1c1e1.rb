class Complement
  CONVERSION = {"C" => "G", "G" => "C", "T" => "A", "A" => "U"}
  LETTERS = /[CGTAU]/
  
  class << self
    def of_dna(strand)
      strand.gsub(LETTERS, CONVERSION)
    end
  
    def of_rna(strand)
      strand.gsub(LETTERS, CONVERSION.invert)
    end
  end
end
