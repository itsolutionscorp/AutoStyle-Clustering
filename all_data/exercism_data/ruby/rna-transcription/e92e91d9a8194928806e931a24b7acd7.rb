class Complement
  @@dna_rna_pairs = { "G"=>"C", "C"=>"G", "T"=>"A", "A"=>"U" }
  def self.of_dna(rna)
    tmp_str = ''
    rna.split(//).each{|str|
      tmp_str << @@dna_rna_pairs[str]
    }
    return tmp_str
  end

  def self.of_rna(dna)
    tmp_str = ''
    dna.split(//).each{|str|
      tmp_str << @@dna_rna_pairs.invert[str]
    }
    return tmp_str
  end



end
