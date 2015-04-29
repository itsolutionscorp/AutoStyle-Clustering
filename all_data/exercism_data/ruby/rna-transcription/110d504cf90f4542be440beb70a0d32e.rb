module Complement

  RNA_COMPLEMENT = {"G" => "C",
                    "C" => "G",
                    "T" => "A",
                    "A" => "U"}
  DNA_COMPLEMENT = RNA_COMPLEMENT.invert

  def self.of_dna(strand)
    strand_array = strand.split("")
    strand_array.collect! { |letter| RNA_COMPLEMENT[letter] }
    strand_array.join
  end

  def self.of_rna(strand)
    strand_array = strand.split("")
    strand_array.collect! { |letter| DNA_COMPLEMENT[letter] }
    strand_array.join
  end

end
