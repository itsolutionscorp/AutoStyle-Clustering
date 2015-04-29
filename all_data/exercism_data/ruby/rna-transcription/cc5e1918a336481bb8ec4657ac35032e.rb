class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T'=> 'A', 'A'=> 'U'}

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    dna.chars.collect {|x|
      DNA_TO_RNA[x] || x
    }.join
  end

  def self.of_rna(rna)
    rna.chars.collect {|x|
      RNA_TO_DNA[x] || x
    }.join
  end
end
