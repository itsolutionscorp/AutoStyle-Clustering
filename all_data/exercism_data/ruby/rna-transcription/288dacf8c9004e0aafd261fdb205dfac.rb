class Complement

  @dna_to_rna = { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U" }

  def self.of_dna(dna)
    strand =  dna.each_char.map do |sequence|
                @dna_to_rna[sequence]
              end
    strand.join
  end

  def self.of_rna(rna)
    strand =  rna.each_char.map do |sequence|
                @dna_to_rna.invert[sequence]
              end
    strand.join
  end

end
