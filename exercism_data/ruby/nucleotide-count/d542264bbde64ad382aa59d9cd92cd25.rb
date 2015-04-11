class DNA

  def initialize(dna_string)
    @dna_string = dna_string
    @dna_string_count = dna_string.length

    @nucleotide_counts = Hash[
      'A' => @dna_string.scan('A').count,
      'C' => @dna_string.scan('C').count,
      'T' => @dna_string.scan('T').count,
      'G' => @dna_string.scan('G').count
    ]

    nucleotide_count = @nucleotide_counts.values.inject(:+)

    if @dna_string_count != nucleotide_count
    raise ArgumentError
    end

  end

  def count(nucleotides)

    @dna = 'ACTG'

    if @dna.scan(nucleotides) == []
      raise ArgumentError
    else
      @dna_string.scan(nucleotides).count
    end

  end

  def nucleotide_counts

    @nucleotide_counts

  end

end
