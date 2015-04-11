class DNA

  attr_accessor :strand

  def initialize(strand)
    @strand = strand.upcase
    ensure_dna_structure
  end

  def count(nucleotide)
    ensure_nucleotide_exists(nucleotide)
    nucleotide_frequency(nucleotide)
  end

  def nucleotide_counts
    @nucleotide_counts ||= nucleotides_frequencies
  end

private

  def nucleotide_factory
    @nucleotide_factory ||= NucleotideFactory.new
  end

  def all_nucleotides
    nucleotide_factory.dna_nucleotides
  end

  def nucleotides
    @nucleotides ||= @strand.chars
  end

  def nucleotides_frequencies
    all_nucleotides.map do |n|
      Hash[n, nucleotide_frequency(n)]
    end.reduce Hash.new, :merge
  end

  def nucleotide_frequency(nucleotide)
    nucleotides.count(nucleotide)
  end

  def ensure_nucleotide_exists(n)
    unless nucleotide_factory.exists?(n)
      raise ArgumentError.new("Existing nucleotides (#{all_nucleotides.join(' ')}) do not include given nucleotide #{n}")
    end
  end

  def ensure_dna_structure
    unless strand_consists_of_dna_nucleotides?
      raise ArgumentError.new("Given strand does not consist exclusively of nucleotides used in dna: #{all_nucleotides.join(' ')}")
    end
  end

  def strand_consists_of_dna_nucleotides?
    (@strand.chars.uniq - all_nucleotides).empty?
  end

end

class NucleotideFactory
  def exists?(n)
    existing_nucleotides.include?(n)
  end

  def existing_nucleotides
    (dna_nucleotides + rna_nucleotides).uniq
  end

  def dna_nucleotides
    @dna_nucleotides ||= %w(A C G T)
  end

  def rna_nucleotides
    @rna_nucleotides ||= %w(A C G U)
  end
end
