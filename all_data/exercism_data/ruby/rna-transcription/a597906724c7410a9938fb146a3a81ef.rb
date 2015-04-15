class Complement
  MAPPING_DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A'=>'U'}
  def self.of_dna(dna)
    dna.chars.inject(''){|_, char| _ << MAPPING_DNA[char]}
  end

  def self.of_rna(rna)
    mapping = {}
    MAPPING_DNA.each {|k, v| mapping[v] = k}
    return rna.chars.inject(''){|_, char| _ << mapping[char]}
  end
end
