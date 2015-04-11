# Given a DNA strand, its transcribed RNA strand is formed by replacing
# each nucleotide with its complement:
#
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
class Complement
  RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(dna_strand)
    complement(dna_strand, RNA)
  end

  def self.of_rna(rna_strand)
    complement(rna_strand, DNA)
  end

  private

  def self.complement(from, to)
    result = ""
    from.chars.each do |c|
      result << to[c]
    end
    result
  end
end
