class Complement
  def self.of_dna(base_chain)
    complement_using(base_chain) { |base| self.dna_complement_base(base) }
  end

  def self.of_rna(base_chain)
    complement_using(base_chain) { |base| self.rna_complement_base(base) }
  end

  private

  def self.complement_using(base_chain, &complement_base)
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
