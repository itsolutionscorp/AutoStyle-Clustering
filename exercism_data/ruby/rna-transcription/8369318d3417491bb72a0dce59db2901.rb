class Complement
  BASEPAIRS = {"RNA"=>{"G"=>"C","C"=>"G","A"=>"U","T"=>"A"},
               "DNA"=>{"C"=>"G","G"=>"C","U"=>"A","A"=>"T"}}

  def self.match_basepair(type, nucleotide)
   BASEPAIRS[type][nucleotide]
  end

  def self.of_dna(sequence)
    result = sequence.split("").inject("") { |result, nucleotide| result + self.match_basepair('RNA',nucleotide) }
  end

  def self.of_rna(sequence)
    result = sequence.split("").inject("") { |result, nucleotide| result + self.match_basepair('DNA',nucleotide) }
  end
end
