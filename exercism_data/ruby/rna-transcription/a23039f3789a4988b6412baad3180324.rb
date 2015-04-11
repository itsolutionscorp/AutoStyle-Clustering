class Complement
  VALID_DNA = "CGTA"
  VALID_RNA = "CGUA"

  def self.validate(strand, complement)
    strand.length.times do |i|
      unless strand[i] != complement[i]
        raise ArgumentError.new("not a valid strand")
      end
    end
    complement
  end

  def self.of_dna(dna)
    complement = dna.tr VALID_DNA, "GCAU"
    validate(dna, complement)
  end

  def self.of_rna(rna)
    complement = rna.tr VALID_RNA, "GCAT"
    validate(rna, complement)
  end
end
