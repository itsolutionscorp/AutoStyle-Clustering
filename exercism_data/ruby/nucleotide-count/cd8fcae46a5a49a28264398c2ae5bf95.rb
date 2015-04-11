class DNA
  attr_reader :sequence, :nucleotide_count

  ADENOSINE = "A"
  CYTIDINE  = "C"
  GUANOSINE = "G"
  THYMIDINE = "T"

  def initialize(sequence)
    @sequence = String(sequence)
    @nucleotide_count = { ADENOSINE: 0, CYTIDINE: 0, GUANOSINE: 0, THYMIDINE: 0 }
  end

  def count(nucleotide)
    raise ArgumentError if illegal_nucleotide?(nucleotide)
    sequence.count(nucleotide)
  end

  def nucleotide_counts
    sequence.chars.each_with_object nucleotide_count, &frequency_counter
  end

  private

  def illegal_nucleotide?(nucleotide)
    return if RNA.new.nucleotide_count.include? nucleotide
    !nucleotide_count.include? nucleotide
  end

  def frequency_counter
    -> (nucleotide, count) { count[nucleotide] += 1 }
  end
end

class RNA
  attr_reader :nucleotide_count

  ADENOSINE = "A"
  CYTIDINE  = "C"
  GUANOSINE = "G"
  URACIL = "U"

  def initialize
    @nucleotide_count = { ADENOSINE => 0, CYTIDINE => 0, GUANOSINE => 0, URACIL => 0 }
  end
end
