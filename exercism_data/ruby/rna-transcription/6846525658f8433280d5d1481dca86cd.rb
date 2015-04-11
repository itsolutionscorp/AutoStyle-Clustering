module Complement
  extend self

  RNA_TO_DNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  DNA_TO_RNA = { "G" => "C", "C" => "G", "U" => "A", "A" => "T" }

  def of_dna(dna)
    dna.each_char.map { |n| RNA_TO_DNA[n] }.join
  end

  def of_rna(rna)
    rna.each_char.map { |n| DNA_TO_RNA[n] }.join
  end

end
