class DNA
  
  attr_reader :sequence
  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    verify_nucleotide(nucleotide)
    sequence.scan(/#{nucleotide}/).count
  end

  def nucleotide_counts
    sequence.chars.each_with_object(default_counter) do |nucleotide, counter|
      counter[nucleotide] += 1
    end
  end

  private

    def all_nucleotides
      ["A", "T", "C", "G", "U"]
    end

    def dna_nucleotides
      ["A", "T", "C", "G"]
    end

    def default_counter
      dna_nucleotides.each_with_object({}) do |nucleotide, counter|
        counter[nucleotide] = 0
      end
    end

    def verify_nucleotide(nucleotide)
      raise ArgumentError unless all_nucleotides.include? nucleotide
    end

end
