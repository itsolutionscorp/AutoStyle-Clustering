class Complement

  def self.of_dna(nucleotides)
    nucleotides.split(//).map {|nucleotide| dna_nucleotide_compliment[nucleotide]}.join
  end

  def self.dna_nucleotide_compliment
    {'G'=>"C","C"=>"G","T"=>"A","A"=>"U"}
  end

  def self.of_rna(nucleotides)
    nucleotides.split(//).map {|nucleotide| rna_nucleotide_compliment[nucleotide]}.join
  end

  def self.rna_nucleotide_compliment
    {'C'=>"G","G"=>"C","A"=>"T","U"=>"A"}
  end
end
