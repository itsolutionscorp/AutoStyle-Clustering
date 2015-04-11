class DNA
  ADENOSINE = 'A'
  CYTIDINE = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URIDINE = 'U'

  def initialize sequence
    @sequence = sequence
  end

  def count nucleotide
    verify_nucleotide nucleotide
    sequence.count nucleotide
  end

  def nucleotide_counts
    dna_necleoides.each_with_object({}) { |letter, hash| hash[letter] = count letter }
  end

  private
  attr_reader :sequence

  def verify_nucleotide nucleotide
    raise ArgumentError unless necleoides.include? nucleotide
  end

  def necleoides
    dna_necleoides + other_necleoides
  end

  def dna_necleoides
    [ADENOSINE, CYTIDINE, GUANOSINE, THYMIDINE]
  end

  def other_necleoides
    [URIDINE]
  end
end
