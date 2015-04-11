class Strand

  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def nucleobases
    string.chars
  end

end

class Hamming

  def self.compute(first_strand_string, second_strand_string)
    first_strand  = Strand.new(first_strand_string)
    second_strand = Strand.new(second_strand_string)
    distance(first_strand, second_strand)
  end

private

  def self.distance(first_strand, second_strand)
    paired_nucleobases = pair_nucleobases(first_strand, second_strand)
    count_differences(paired_nucleobases)
  end

  def self.count_differences(paired_nucleobases)
    paired_nucleobases.count do |nucleobase_1, nucleobase_2|
      nucleobase_1 != nucleobase_2
    end
  end

  def self.pair_nucleobases(first_strand, second_strand)
    first_strand.nucleobases.zip second_strand.nucleobases
  end

end
