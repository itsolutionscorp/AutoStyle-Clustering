class DNA
  attr_reader :nukes

  def initialize(dna)
    @nukes = dna.chars
    check_for_uracil(nukes)
    validate(nukes)
  end

  def count(match)
    validate([match])
    return 0 if match == 'U'
    nucleotide_counts[match]
  end

  def nucleotide_counts(string="default")
    counts_hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

    nukes.each_with_object(counts_hash) do |nuke|
      counts_hash[nuke] += 1
    end
  end

  private

  def check_for_uracil(input)
    raise ArgumentError if input.include?('U')
  end

  def validate(input)
    raise ArgumentError unless input.all? {|n| nucleotides.include?(n)}
  end

  def nucleotides
    ['A', 'T', 'C', 'G', 'U']
  end

end
