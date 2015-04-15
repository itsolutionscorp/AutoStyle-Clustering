class DNA
  attr_reader :nucleotides

  def initialize(dna)
    @nucleotides = dna.chars
    check_for_uracil(nucleotides)
    validate(nucleotides)
  end

  def count(match)
    validate([match])
    return 0 unless dna_nucleotides.include?(match)
    nucleotide_counts[match]
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

    nucleotides.each_with_object(counts) do |nucleotides|
      counts[nucleotides] += 1
    end
  end

  private

  def check_for_uracil(input)
    raise ArgumentError if input.include?('U')
  end

  def validate(input)
    raise ArgumentError unless input.all? {|n| all_nucleotides.include?(n)}
  end

  def all_nucleotides
    ['A', 'T', 'C', 'G', 'U']
  end

  def dna_nucleotides
    all_nucleotides - ['U']
  end

end
