class Complement

  def self.of_dna(base_chain)
    complement_chain(base_chain, DNA_COMPLEMENT_BASE_PROC)
  end

  def self.of_rna(base_chain)
    complement_chain(base_chain, RNA_COMPLEMENT_BASE_PROC)
  end

  private

  DNA_COMPLEMENT_BASE_PROC = lambda { |base| self.dna_complement_base(base) }
  RNA_COMPLEMENT_BASE_PROC = lambda { |base| self.rna_complement_base(base) }

  def self.complement_chain(base_chain, complement_base)
    complement = ""
    base_chain.chars.each do |base|
      complement += complement_base.call(base)
    end
    complement
  end

  def self.rna_complement_base(base)
    case base
    when "C"
      "G"
    when "G"
      "C"
    when "U"
      "A"
    when "A"
      "T"
    end
  end

  def self.dna_complement_base(base)
    case base
    when "C"
      "G"
    when "G"
      "C"
    when "T"
      "A"
    when "A"
      "U"
    end
  end
end
