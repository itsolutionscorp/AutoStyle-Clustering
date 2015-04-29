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
    translate string, DNA_TO_RNA
  end

  def of_rna( string )
    translate string, RNA_TO_DNA
  end


  def translate( sequence, with_map )
    sequence.chars.map do |nucleotide|
      with_map.fetch( nucleotide ) do
        raise ArgumentError, "No complement of '#{nucleotide}'"
      end
    end.join
  end
end
