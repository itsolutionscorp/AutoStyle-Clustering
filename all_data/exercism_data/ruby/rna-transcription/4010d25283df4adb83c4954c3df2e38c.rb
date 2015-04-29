class Complement
  VALID_DNA = "CGTA"
  VALID_RNA = "CGUA"

  def self.validate(strand, compliment)
    strand.length.times do |i|
      unless strand[i] != compliment[i]
        raise ArgumentError.new("not a valid strand")
      end
    end
    compliment
  end

  def self.of_dna(dna)
    compliment = dna.tr VALID_DNA, "GCAU"
    validate(dna, compliment)
  end

  def self.of_rna(rna)
    compliment = rna.tr VALID_RNA, "GCAT"
    validate(rna, compliment)
  end
end
