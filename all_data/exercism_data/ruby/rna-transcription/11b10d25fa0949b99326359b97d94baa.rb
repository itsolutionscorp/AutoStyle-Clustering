class Complement

  def self.of_dna(strand)
    translate(strand, :rna)
  end

  def self.of_rna(strand)
    translate(strand, :dna)
  end

  private

  def self.translate(strand, outcome_type)
    output = ''
    nucleotides = {"G" => "C", "C" => "G", "T" => "A", "U" => "A"}
    nucleotides["A"] = (outcome_type == :rna) ? "U" : "T"
    strand.each_char do |s|
      output << nucleotides[s]
    end
    output
  end

end
