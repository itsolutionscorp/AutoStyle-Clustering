module Complement
  module_function

  DNA_TO_RNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }.freeze

  RNA_TO_DNA = DNA_TO_RNA.invert.freeze

  def of_dna( string )
    string.chars.map do |nucleotide|
      DNA_TO_RNA.fetch( nucleotide ) do
        raise ArgumentError, "No complement of '#{nucleotide}'"
      end
    end.join
  end

  def of_rna( string )
    string.chars.map do |nucleotide|
      RNA_TO_DNA.fetch( nucleotide ) do
        raise ArgumentError, "No complement of '#{nucleotide}'"
      end
    end.join
  end
end
