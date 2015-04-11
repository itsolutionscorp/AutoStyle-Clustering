class Hamming

  #** Exercise: Describe the solution **
  #Hamming's compute method finds the distance between two strands of DNA.
  #It compares each base from the first sample to the base occupying the same sequence position in the second sample.
  #If the bases are different, it is noted.
  #If one sample is longer than the other, the extra bases in the larger sample are ignored.
  #The sum of the diffences is presented as the distance.

  def self.compute(strand_a, strand_b)
    distance = 0
    strand_a.each_char.zip(strand_b.each_char) do |base_of_strand_a, base_of_strand_b|
      break if base_of_strand_b == nil
      distance += 1 if base_of_strand_a != base_of_strand_b
    end
    distance
  end

end
