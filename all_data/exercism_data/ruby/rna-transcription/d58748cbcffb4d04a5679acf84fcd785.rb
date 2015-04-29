class Complement
  def self.of_dna(dna_string)
    nucleotide_compliment = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
    string = ""
    dna_string.each_char { |char| string << nucleotide_compliment[char] }
    string
  end

  def self.of_rna(rna_string)
    nucleotide_compliment = {"C" => "G", "G" => "C", "A" => "T", "U" => "A"}
    string = ""
    rna_string.each_char { |char| string << nucleotide_compliment[char] }
    string
  end
end

# DNA      RNA
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`


#answer = dna_string.each_char.map { |char| nucleotide_compliment[char] }
#is there a method like map for creating new strings? use join?
