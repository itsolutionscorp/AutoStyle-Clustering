# Returns an RNA complement for a given DNA strand.

# Third attempt, using array methods, thanks to nitpick by revdan
class Complement

  def self.of_dna dna
    dna.chars.map { |char| substitute[char]}.join
  end

  def self.of_rna rna
    rna.chars.map { |char| substitute.key(char)}.join
  end

  def self.substitute
    {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  end

end
