class Hamming

  #compute Hamming Distance between two strands
  def self.compute(first_strand, second_strand)
    raise ArgumentError, "Length of the two strands should be equal"
    for_every_element_in(first_strand).count do |index|
      first_strand[index] != second_strand[index]
    end
  end

  private
  #returns a range of integers whose maximum value is the same as the length
  #of the strand itself
  def self.for_every_element_in(strand)
    (0..strand.length)
  end

end
