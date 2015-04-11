class Complement
  
  COMP_MAP = {"G" => "C", "C" => "G", "T"=> "A", "A"=> "U"}
  REVERSE_COMP_MAP = COMP_MAP.to_a.reduce({}){ |a,o| a.merge({o[1]=> o[0]}) }  
  
  def self.of_dna(dna)
    dna.split("").map{|c| COMP_MAP[c] }.join()
  end
  
  def self.of_rna(rna)
    rna.split("").map{|c| REVERSE_COMP_MAP[c] }.join()
  end   
  
end  
