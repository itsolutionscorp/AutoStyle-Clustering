class Hamming

  def self.compute dna1, dna2
    difference = 0
    
    dna1, dna2 = get_arrays_of_equal_length_for dna1, dna2

    (0..dna1.length-1).each do |index|
      difference += 1 if dna1[index] != dna2[index]
    end

    return difference
  end

  private
  def self.get_arrays_of_equal_length_for dna1, dna2
    minsize = [dna1.length, dna2.length].min
    dna1 = dna1[0..minsize-1]
    dna2 = dna2[0..minsize-1]
    return [dna1, dna2]
  end

end
