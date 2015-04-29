class Complement
  DNA_RNA = {G: "C", C: "G", A: "T", U: "A"}
  RNA_DNA = {G: "C", C: "G", A: "U", T: "A"}

  def self.of_dna(dna)
    tabdna = dna.chars
    (tabdna.map { |i| DNA_RNA.key(i) }).join
  end

  def self.of_rna(rna)
    tabdna = rna.chars
    (tabdna.map { |i| RNA_DNA.key(i)}).join
  end

end
