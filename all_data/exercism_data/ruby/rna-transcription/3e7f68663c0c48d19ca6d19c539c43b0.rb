class Complement

  @rna_to_dna = { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U" }

  def self.of_dna(dna)
    strnd =  dna.each_char.map do |strand|
                  @rna_to_dna[strand]
                end
    strnd.join
  end

  def self.of_rna(rna)
    strnd = rna.each_char.map do |strand|
              @rna_to_dna.invert[strand]
            end
    strnd.join
  end

end
