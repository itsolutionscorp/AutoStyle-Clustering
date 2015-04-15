module Complement
  def self.of_dna(sequence)
    sequence.chars.collect! do |dna_base|
      to_rna_base(dna_base)
    end.join
  end

  def self.of_rna(sequence)
    sequence.chars.collect! do |rna_base|
      to_dna_base(rna_base)
    end.join
  end

  private

  def self.to_rna_base(base)
    case (base)
    when 'A'
      return 'U'
    when 'C'
      return 'G'
    when 'G'
      return 'C'
    when 'T'
      return 'A'
    end
  end

  def self.to_dna_base(base)
    case (base)
    when 'A'
      return 'T'
    when 'C'
      return 'G'
    when 'G'
      return 'C'
    when 'U'
      return 'A'
    end
  end
end
