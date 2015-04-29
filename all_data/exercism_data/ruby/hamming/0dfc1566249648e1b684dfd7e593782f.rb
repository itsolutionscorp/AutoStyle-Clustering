class Hamming

  def self.compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    counter = 0

    zipped_strands = strand1.zip(strand2)

    zipped_strands.each do |x,y|
      if x == nil || y == nil
        nil
      elsif [x] != [y]
        counter += 1
      end
    end
    counter
  end
end
