class Complement

  def self.of_dna nuc
    nuc.gsub(/[GCTA]/, { "G" => "C", "C" => "G" ,"T" => "A" , "A" => "U" })
  end

  def self.of_rna nuc
   nuc.gsub(/[CGAU]/, { "G" => "C", "C" => "G" ,"A" => "T" , "U" => "A" })
  end

end
