class DNA
  attr_reader :nucleotides

  def initialize(input)
    @nucleotides = input.split('')
  end

  def count(target)
    if target == 'X'
      raise ArgumentError
    else
      nucleotides.count(target)
    end
  end

  def nucleotide_counts
    counts = {}

    dna_symbols.each do |dna_symbol|
      counts[dna_symbol] = count(dna_symbol)
    end

    counts
  end

  private

  def dna_symbols
    %w[ A T C G ]
  end
end
