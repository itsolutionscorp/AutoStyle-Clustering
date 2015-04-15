# Rna Transcription

# Write a program that, given a DNA strand, returns its RNA complement (per RNA transcription).

# Both DNA and RNA strands are a sequence of nucleotides.

# The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and thymine (**T**).

# The four nucleotides found in RNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and uracil (**U**).

# Given a DNA strand, its transcribed RNA strand is formed by replacing
# each nucleotide with its complement:

# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
class Complement

  def self.of_dna(string)
    if string.chars.all? { |x| /[GCTA]/.match(x)}
      old = "GCTA"
      new = "CGAU"
      string.tr(old, new)
    else
      raise ArgumentError.new("Wrong input")
    end
  end

  def self.of_rna(string)
    if string.chars.all? { |x| /[CGAU]/.match(x)}
      old = "CGAU"
      new = "GCTA"
      string.tr(old, new)
    else
      raise ArgumentError.new("wrong input")
    end
  end

end
