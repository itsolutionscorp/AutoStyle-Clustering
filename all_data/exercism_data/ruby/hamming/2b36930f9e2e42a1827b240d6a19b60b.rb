class Hamming

  #A program for calculating the point mutations between two strands of DNA
  #Note: The &&y on 12 ensures that the zip method does not include nil in zipped
  #arrays with the strand that has extra letters

  def self.compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    zipped_strands = strand1.zip(strand2)

    zipped_strands.count do |x,y|
      x != y && y
    end
  end
end
