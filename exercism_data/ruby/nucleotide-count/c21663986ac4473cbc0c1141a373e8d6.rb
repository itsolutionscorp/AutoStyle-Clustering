require 'set'

class NucleicAcid
  ADENINE = ?A
  CYTOSINE = ?C
  GUANINE = ?G
  THYMINE = ?T
  URACIL = ?U

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    validate(nucleotide)
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    @_counts ||= nucleotides.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  private
  def validate(nucleotide)
    unless valid_nucleotides.include? nucleotide
      raise ArgumentError, "Nucleotide #{nucleotide} must be one of #{valid_nucleotides}"
    end
  end

  def valid_nucleotides
    (DNA::NUCLEOTIDES + RNA::NUCLEOTIDES).to_a
  end
end

class DNA < NucleicAcid
  NUCLEOTIDES = Set.new([
    ADENINE,
    CYTOSINE,
    GUANINE,
    THYMINE,
  ])

  private
  def nucleotides
    NUCLEOTIDES
  end
end

class RNA < NucleicAcid
  NUCLEOTIDES = Set.new([
    ADENINE,
    CYTOSINE,
    GUANINE,
    URACIL,
  ])

  private
  def nucleotides
    NUCLEOTIDES
  end
end
