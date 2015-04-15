class Strand

  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def distance_from(second_strand)
    paired_nucleobases = pair_nucleobases(self, second_strand)
    count_differences(paired_nucleobases)
  end

protected

  def nucleobases
    string.chars
  end

private

  def count_differences(paired_nucleobases)
    paired_nucleobases.count do |nucleobase_1, nucleobase_2|
      nucleobase_1 != nucleobase_2
    end
  end

  def pair_nucleobases(first_strand, second_strand)
    first_strand.nucleobases.zip second_strand.nucleobases
  end

end

class Hamming

  def self.compute(first_strand_string, second_strand_string)
    first_strand  = Strand.new(first_strand_string)
    second_strand = Strand.new(second_strand_string)
    first_strand.distance_from(second_strand)
  end

end
