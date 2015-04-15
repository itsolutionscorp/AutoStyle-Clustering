

class Complement
  RNA_of_DNA={"G"=> "C", "C"=>"G", "T"=>"A", "A"=>"U"}
  DNA_of_RNA=RNA_of_DNA.invert

  DNA_elements = /[GCTA]/
  RNA_elements = /[CGAU]/

  def self.of_dna(dna)
    rna=dna.gsub(DNA_elements, RNA_of_DNA)
  end

  def self.of_rna(rna)
    dna=rna.gsub(RNA_elements, DNA_of_RNA)
  end
end
