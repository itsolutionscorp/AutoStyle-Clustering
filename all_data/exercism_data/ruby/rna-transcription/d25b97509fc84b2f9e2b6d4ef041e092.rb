# Returns an RNA complement for a given DNA strand.

# Third attempt, using array methods, thanks to nitpick by revdan

# Second attempt, separating the substitution hash into a separate method
# and simplifying the of_rna method to use the same hash.
class Complement

  def self.of_dna dna
    rna = ''
    dna.each_char { |char| rna << substitute[char]}
    rna
  end

  def self.of_rna rna
    dna = ''
    rna.each_char { |char| dna << substitute.key(char)}
    dna
  end

  def self.substitute
    {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  end

end


# # First attempt, using string methods
# class Complement

#   def self.of_dna dna
#     substitute = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
#     rna = ''
#     dna.each_char { |char| rna << substitute[char]}
#     rna
#   end

#   def self.of_rna rna
#     substitute = {"G" => "C", "C" => "G", "A" => "T", "U" => "A"}
#     dna = ''
#     rna.each_char { |char| dna << substitute[char]}
#     dna
#   end

# end
