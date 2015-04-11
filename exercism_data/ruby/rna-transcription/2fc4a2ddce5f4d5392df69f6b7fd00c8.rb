class Complement
  @@dna_rna_pairs = { "G"=>"C", "C"=>"G", "T"=>"A", "A"=>"U" }
  def self.of_dna(rna)
    tmp_str = ''
    rna.chars.each do |str|
      tmp_str << @@dna_rna_pairs[str]
    end
    return tmp_str
  end

  def self.of_rna(dna)
    tmp_str = ''
    dna.chars.each do |str|
      tmp_str << @@dna_rna_pairs.invert[str]
    end
    return tmp_str
  end



end
