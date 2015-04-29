class Complement
  def self.to_rna_base(base)
    case(base)
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
    case(base)
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

  def self.of_dna(dna_sequence)
    @rna_sequence = Array.new(dna_sequence.length)
    dna_sequence.chars.collect.with_index do |dna_base, index|
      @rna_sequence[index] = self.to_rna_base(dna_base)
    end
    return @rna_sequence.join
  end

  def self.of_rna(rna_sequence)
    @dna_sequence = Array.new(rna_sequence.length)
    rna_sequence.chars.collect.with_index do |rna_base, index|
      @dna_sequence[index] = self.to_dna_base(rna_base)
    end
    return @dna_sequence.join
  end
end
