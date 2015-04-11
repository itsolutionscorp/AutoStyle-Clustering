# Given a DNA strand, its transcribed RNA strand is formed by replacing
# each nucleotide with its complement:
#
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
# 
# Conversely, RNA is translated to DNA in via the inverse values.
# 
class Complement

  TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  # Translates a strand of DNA.
  def self.of_dna dna
    translate dna, TRANSLATION
  end

  # Translates a strand of RNA, the inverse of DNA.
  def self.of_rna rna
    translate rna, TRANSLATION.invert
  end

  # Translates an arbitrary strand given an arbitrary translation
  def self.translate strand, translation
    strand.chars.map! { |i|
      translation[strand[i]]
    }.join
  end

  private_class_method :translate

end
