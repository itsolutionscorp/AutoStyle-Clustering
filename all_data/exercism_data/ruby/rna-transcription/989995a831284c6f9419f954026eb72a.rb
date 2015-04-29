# Returns an RNA complement for a given DNA strand.
class Complement

  def self.of_dna dna
    substitute = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
    rna = ''
    dna.each_char { |char| rna << substitute[char]}
    rna
  end

  def self.of_rna rna
    substitute = {"G" => "C", "C" => "G", "A" => "T", "U" => "A"}
    dna = ''
    rna.each_char { |char| dna << substitute[char]}
    dna
  end

end
