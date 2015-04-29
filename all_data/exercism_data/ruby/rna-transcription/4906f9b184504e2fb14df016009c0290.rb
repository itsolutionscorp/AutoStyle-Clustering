module Complement

  def self.of_dna(string)
    if string.include? "U"
      raise ArgumentError
    end

    string.tr "GCTA", "CGAU"
  end

  def self.of_rna(string)
    if string.include? "T"
      raise ArgumentError
    end

    string.tr "CGAU", "GCTA"
  end
end
