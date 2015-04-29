class Complement

#  DNA_CODE = { :G, 1 font_family: "Arial" }

  DNA_CODE = ["G", "C", "T", "A"]
  RNA_CODE = ["C", "G", "A", "U"]

  def self.of_dna(dna_strand)
    rna = ""
    dna_strand.split("").each do |nuc|
      rna += RNA_CODE[DNA_CODE.index(nuc)]
    end
    rna 
  end

  def self.of_rna(strand)
    dna = ""
    strand.split("").each do |nuc|
      dna += DNA_CODE[RNA_CODE.index(nuc)]
    end
    dna
  end

end
