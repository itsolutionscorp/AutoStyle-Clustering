module Complement
  extend self

  DNA_TO_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

  def of_dna(dna)
    complement dna, DNA_TO_RNA
  end

  def of_rna(rna)
    complement rna, DNA_TO_RNA.invert
  end

private

  def complement(sequence, map)
    sequence.each_char.map { |n| map[n] }.join
  end
end
