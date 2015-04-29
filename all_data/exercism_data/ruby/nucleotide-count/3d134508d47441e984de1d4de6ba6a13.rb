class DNA
  
  attr_reader :sequence
  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    verify_nucleotide(nucleotide)
    counter.count_nucleotide_occurences(nucleotide)
  end

  def nucleotide_counts
    counter.count_all_nucleotides(dna_nucleotides)
  end

  private

    def counter
      @counter ||= Counter.new(sequence)
    end

    def all_nucleotides
      ["A", "T", "C", "G", "U"]
    end

    def dna_nucleotides
      ["A", "T", "C", "G"]
    end

    def verify_nucleotide(nucleotide)
      raise ArgumentError unless all_nucleotides.include? nucleotide
    end
    
end


class Counter

  attr_reader :sequence
  def initialize(sequence)
    @sequence = sequence
  end

  def count_nucleotide_occurences(nucleotide)
    sequence.scan(/#{nucleotide}/).count
  end

  def count_all_nucleotides(valid_nucleotides)
    nucleotides_counter = default_counter(valid_nucleotides)
    sequence.chars.each_with_object(nucleotides_counter) do |nucleotide, counter|
      counter[nucleotide] += 1
    end
  end

  private

    def default_counter(nucleotides)
      nucleotides.each_with_object({}) do |nucleotide, counter|
        counter[nucleotide] = 0
      end
    end

end
