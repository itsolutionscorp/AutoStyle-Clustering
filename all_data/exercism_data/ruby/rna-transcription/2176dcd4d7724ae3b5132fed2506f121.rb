class Complement
  COMPLEMENT_DNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  COMPLEMENT_RNA = COMPLEMENT_DNA.invert

  def self.complement(strand, type=:dna)
    @string = ""
    strand.each_char do |n|
        @string += (type == :dna) ? COMPLEMENT_DNA[n] : COMPLEMENT_RNA[n]
    end
    @string
  end

  def self.of_dna(dna_string)
    self.complement(dna_string)
  end

  def self.of_rna(rna_string)
    self.complement(rna_string, :rna)
  end
end
