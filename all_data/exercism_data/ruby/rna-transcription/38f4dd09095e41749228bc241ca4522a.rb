module Complement
  module_function

  def of_dna(strand)
    strand.gsub(/./) { |nucleotide| transcriptor[nucleotide] }
  end

  def of_rna(strand)
    strand.gsub(/./) { |nucleotide| transcriptor.invert[nucleotide] }
  end

  def transcriptor
    { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  end
  private_class_method :transcriptor
end
