

class Complement
  RNA_of_DNA={"G"=> "C", "C"=>"G", "T"=>"A", "A"=>"U"}
  DNA_of_RNA=RNA_of_DNA.invert
  
  def self.of_dna(dna)
    rna=dna.gsub(/[ATCG]/, RNA_seq)
  end
  
  def self.of_rna(rna)
    dna=rna.gsub(/[CGAU]/,DNA_seq)
  end
end
#"C"=> "G", "G"=>"C", "A"=>"T", "U"=>"A"
