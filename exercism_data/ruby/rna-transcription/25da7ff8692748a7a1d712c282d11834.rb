class DNA
  attr_reader :dna_strand

  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def to_rna
    convert_thymidines_to_uracils
  end

  def convert_thymidines_to_uracils
    thymidines_to_uracils.each do |thymidine, uracil|
      dna_strand.gsub!(thymidine, uracil)
    end

    dna_strand
  end

  def thymidines_to_uracils
    {
      "T" => "U"
    }
  end
end
