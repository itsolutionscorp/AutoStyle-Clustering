

class Complement
  def self.of_dna(dna)
    rna=dna.gsub(/[ATCG]/, "G"=> "C", "C"=>"G", "T"=>"A", "A"=>"U")
  end
  
  def self.of_rna(rna)
    dna=rna.gsub(/[CGAU]/,"C"=> "G", "G"=>"C", "A"=>"T", "U"=>"A")
  end
end
