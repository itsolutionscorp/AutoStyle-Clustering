class Hamming

  def initialize(strand)
    @strand = strand
  end

  def self.compute(strand_one, strand_two)
    count = 0
    hammes = 0
    strand_one_array = strand_one.split("")
    strand_two_array = strand_two.split("")

    until count == distance(strand_one_array, strand_two_array)
      if strand_one_array[count] != strand_two_array[count]
        hammes += 1
        count += 1
      else
        count += 1
      end
    end
    hammes
  end

  def self.distance(strand_one, strand_two)
    if strand_one.length >= strand_two.length
      distance = strand_two.length
    else
      distance = strand_one.length
    end
    distance
  end

end
