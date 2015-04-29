class Hamming

  attr_accessor :first_strand, :second_strand

  def initialize(first_strand, second_strand)
    @first_strand = first_strand
    @second_strand = second_strand
  end

  def distance
    zipped.count do |a, b|
      a != b
    end
  end

  def self.compute(first_strand, second_strand)
    new(first_strand, second_strand).distance
  end

private

  def zipped
    chars(first_strand).zip chars(second_strand)
  end

  def chars(string)
    string.split('')
  end

end
