class DNA
  attr_reader :dna, :nucleotides

  def initialize(dna)
    @dna = dna
    @nucleotides = ['A', 'T', 'C', 'G', 'U']
    validate(nukes_in)
  end

  def count(string)
    validate(string.chars)
    return 0 if string.chars.include?('U')
    nucleotide_counts[string]
  end

  def nucleotide_counts(string="default")
    counts_hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}

    nukes_in.each_with_object(counts_hash) do |nuke|
      counts_hash[nuke] += 1
    end

    counts_hash
  end

  private

  def nukes_in
    raise ArgumentError if dna.chars.include?('U')
    dna.chars
  end

  def validate(input)
    raise ArgumentError unless input.all? {|n| nucleotides.include?(n)}
  end

end
