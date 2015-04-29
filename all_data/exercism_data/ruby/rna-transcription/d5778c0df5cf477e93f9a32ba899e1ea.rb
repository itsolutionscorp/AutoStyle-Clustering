class Complement

  MAPPINGS = {
    G: :C,
    C: :G,
    T: :A,
    A: :U
  }

  def self.of_dna(dna)
    new.of_dna(dna)
  end

  def self.of_rna(rna)
    new.of_rna(rna)
  end

  def of_dna(dna)
    complement_for(dna, :dna)
  end

  def of_rna(rna)
    complement_for(rna, :rna)
  end

  private

  def normalize(strand)
    strand.upcase
  end

  def complement_for(strand, type)
    normalize(strand).chars.collect do |char|
      if type == :dna
        MAPPINGS[char.to_sym]
      elsif type == :rna
        MAPPINGS.key(char.to_sym)
      end
    end.join.to_s
  end

end
