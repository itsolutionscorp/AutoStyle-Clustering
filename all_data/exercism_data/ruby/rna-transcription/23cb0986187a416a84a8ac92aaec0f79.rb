class Complement

    DNA_TO_RNA = {"C" => "G", "G" => "C", "T" => "A", "A" => "U"}
    RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)

    dna.chars.map {|x| DNA_TO_RNA[x]}.join

  end

  def self.of_rna(rna)

    rna.chars.map {|x| RNA_TO_DNA[x]}.join

  end
  
end
