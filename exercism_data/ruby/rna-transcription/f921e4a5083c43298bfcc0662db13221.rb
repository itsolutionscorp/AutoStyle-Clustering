module Complement
  module_function

  def of_dna(strand)
    transcribe(strand, dna_to_rna)
  end

  def of_rna(strand)
    transcribe(strand, rna_to_dna)
  end

  def transcribe(strand, direction)
    strand.gsub(/./, direction)
  end

  def dna_to_rna
    { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  end

  def rna_to_dna
    dna_to_rna.invert
  end

  private_class_method :transcribe, :dna_to_rna, :rna_to_dna
end
