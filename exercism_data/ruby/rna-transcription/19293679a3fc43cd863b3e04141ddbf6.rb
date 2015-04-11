class Complement

  @rna_to_dna = { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U" }

  def self.of_dna(dna)
    strand =  dna.each_char.map do |sequence|
                @rna_to_dna[sequence]
              end
    strand.join
  end

  def self.of_rna(rna)
    strand =  rna.each_char.map do |sequence|
                @rna_to_dna.invert[sequence]
              end
    strand.join
  end

end
