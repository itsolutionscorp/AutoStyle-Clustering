class DNA
  attr_reader :nucleotides

  def initialize(input)
    @nucleotides = input.split('')
  end

  def count(target)
    verify_nucleotide(target)
    nucleotides.count(target)
  end

  def nucleotide_counts
    counts = {}

    dna_symbols.each do |dna_symbol|
      counts[dna_symbol] = count(dna_symbol)
    end

    counts
  end

  private

  def verify_nucleotide(target)
    unless valid_nucleotides.include?(target)
      raise ArgumentError
    end
  end

  def valid_nucleotides
    dna_symbols + rna_symbols
  end

  def dna_symbols
    %w[ A T C G ]
  end

  def rna_symbols
    %w[ U ]
  end
end
