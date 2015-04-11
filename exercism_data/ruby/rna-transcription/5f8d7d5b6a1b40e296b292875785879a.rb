module Complement

  RNA_COMPLEMENT = {"G" => "C",
                    "C" => "G",
                    "T" => "A",
                    "A" => "U"}

  def self.of_dna(strand)
    strand.chars.collect! { |l| RNA_COMPLEMENT[l] } .join
  end

  def self.of_rna(strand)
    strand.chars.collect! { |l| RNA_COMPLEMENT.invert[l] } .join
  end

end
