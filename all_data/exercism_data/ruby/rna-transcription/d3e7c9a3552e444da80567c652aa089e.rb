class Complement
  DNA = "CGTA"
  RNA = "GCAU"

  def self.of_dna(string)
    string.tr(DNA, RNA)
  end

  def self.of_rna(string)
    string.tr(RNA, DNA)
  end

end
