class Hamming

  def self.compute(strand_one, strand_two)
    new.compute(strand_one, strand_two)
  end

  def compute(strand_one, strand_two)
    # set initial distance to 0
    # assuming no mutations
    distance = 0
    
    # Convert strands to char arrays
    # zip the two arrays for easy comparison
    comparison_array = strand_one.chars.zip(strand_two.chars)

    # check for differences
    # distance += 1 for each difference
    comparison_array.each do |i|
      next if i[0] === i[1]
      next if i[0].nil? || i[1].nil?
      distance +=1
    end

    # return the computed distance
    distance
  end
end
