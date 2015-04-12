require 'pry'

class Hamming
  def compute(strand1, strand2)
    point_mutations = 0

    nucleotides2 = strand2.chars
    nucleotides1 = strand1.chars.slice(0, nucleotides2.length)
    nucleotide_pairs = nucleotides1.zip(nucleotides2)

    nucleotide_pairs.each do |nucleotide1, nucleotide2|
      point_mutations += 1 unless nucleotide1 == nucleotide2
    end

    point_mutations
  end
end
