class Complement
  def self.of_dna(dna)
    dna.to_s.upcase.gsub("G", ".")
                   .gsub("C", "G")
                   .gsub(".", "C")
                   .gsub("A", "U")
                   .gsub("T", "A")
  end

  def self.of_rna(rna)
    rna.to_s.upcase.gsub("G", ".")
                   .gsub("C", "G")
                   .gsub(".", "C")
                   .gsub("A", "T")
                   .gsub("U", "A")
  end
end
