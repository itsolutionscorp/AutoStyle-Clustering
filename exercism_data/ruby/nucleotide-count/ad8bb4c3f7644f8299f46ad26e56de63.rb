class Nucleotide
  attr_reader :base_letter

  def initialize(base_letter)
    @base_letter = base_letter
  end

  def ==(other_object)
    @base_letter == other_object.base_letter
  end

  def to_s
    @base_letter
  end
end

class NucleicAcidSequence

  def self.convert_sequence_letters(sequence_letters)
    sequence_letters.chars.each_with_object([]) do |base_letter, array|
      array << Nucleotide.new(base_letter)
    end
  end

  ALL_BASES = convert_sequence_letters "ACGTU"

  def initialize(sequence_letters="")
    @base_sequence = NucleicAcidSequence::convert_sequence_letters(sequence_letters)
  end

  def bases
    raise NotImplementedError, "Instances of #{self.class} must respond to #bases"
  end

  def count(base_letter)
    base = Nucleotide.new base_letter
    raise ArgumentError, "Unknown base: #{base.inspect}" unless ALL_BASES.include? base
    @base_sequence.count base
  end

  def nucleotide_counts
    bases.each_with_object({}) { |base, counts| counts[base.to_s] = count(base.to_s) }
  end
end

class RNA < NucleicAcidSequence
  def all_except_thymine
    ALL_BASES.reject { |base| base == Nucleotide.new("T") }
  end

  def bases
    @accepted_bases ||= all_except_thymine
  end
end

class DNA < NucleicAcidSequence
  def all_except_uracil
    ALL_BASES.reject { |base| base == Nucleotide.new("U") }
  end

  def bases
    @accepted_bases ||= all_except_uracil
  end
end
