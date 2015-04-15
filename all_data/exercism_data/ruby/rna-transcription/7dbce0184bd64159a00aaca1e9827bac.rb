class DNA < Struct.new(:strand)

  REPLACEMENTS = {    
    "C" => "C",
    "G" => "G",
    "A" => "A",
    "T" => "U"
  }

  def to_rna
    @rna ||= REPLACEMENTS.inject(strand) do |rna, (nucleotide, replacement)|
      rna.gsub(nucleotide, replacement) 
    end
  end
end
