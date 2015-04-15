class DNA
  def initialize(dna)
    if is_dna?(dna) == nil
      raise ArgumentError.new("That is not DNA")
    end
      @dna = dna
  end

  def count(letter)
    if is_nucleotide?(letter)
      raise ArgumentError.new("That is not a nucleotide")
    end
    @dna.chars.count(letter)
  end

  def nucleotide_counts
    total = Hash.new
    nucleotides = ["G", "A", "T", "C"]
    nucleotides.each do |nucleotide|
      total[nucleotide] = @dna.chars.count(nucleotide)
    end
    total
  end

  private

  def is_dna?(dna)
    dna.match(/^[ACGT]*$/)
  end

  def is_nucleotide?(letter)
    letter != ("A") && letter != ("T") && letter != ("C") && letter != ("G") && letter !=("U") && letter !=('')
  end
end
