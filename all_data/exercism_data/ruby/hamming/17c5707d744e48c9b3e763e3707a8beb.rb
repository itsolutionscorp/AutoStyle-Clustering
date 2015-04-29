class Hamming
  attr_reader :first_strand, :second_strand

  def self.compute(*args)
    new(*args).distance
  end

  def initialize(first_strand, second_strand)
    @first_strand, @second_strand = first_strand, second_strand
  end

  def distance
    first_strand.split("").each_with_index.count do |letter, index|
      second_strand[index] != letter
    end
  end
end
