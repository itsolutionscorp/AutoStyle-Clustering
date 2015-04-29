class Hamming

  #This method takes the two strands and combines them into a zipped array.
  #It then takes the zipped array and counts the letters that do not match.
  #The &&y ensures that any extra letters between the two strands are ignored by ignoring nil

  def self.compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    zipped_strands = strand1.zip(strand2)

    zipped_strands.count do |x,y|
      [x] != [y] && y
    end
  end
end
