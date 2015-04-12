class Hamming

  def compute(dna1, dna2)

    # Convert strings to array
    dna1array = dna1.split(//)
    dna2array = dna2.split(//)

    # For Scoping
    result = []

    # For each in the array compare to same index in other array
    # if they are not equal add to result

    dna1array.map.with_index do |x, i|
      if i < dna2array.count and x != dna2array[i]
        result << dna2array[i]
      end
    end

    # returns the total of the different values at each index
    result.count

  end

end
