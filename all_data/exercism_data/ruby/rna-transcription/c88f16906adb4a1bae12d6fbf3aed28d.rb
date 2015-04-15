class Complement

  TRANSLATION = {"C" => "G", "G" => "C", "T" => "A", "A" => "U"}
  REVERSE_TRANSLATION = TRANSLATION.invert

  def self.of_dna nucleotides
    nucleotides.count("U") > 0 ? raise(ArgumentError, "This is RNA not DNA") : nucleotides.chars.map {|nt| translate(nt, TRANSLATION)}.join
  end

  def self.of_rna nucleotides
    nucleotides.count("T") > 0 ? raise(ArgumentError, "This is DNA not RNA") : nucleotides.chars.map {|nt| translate(nt, REVERSE_TRANSLATION)}.join
  end

  private

  def self.translate nt, direction
    direction[nt]
  end

end
