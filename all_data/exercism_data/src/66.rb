def compute(dna1, dna2)


    dna1array = dna1.split(//)
    dna2array = dna2.split(//)


    result = []




    dna1array.map.with_index do |x, i|
      if i < dna2array.count and x != dna2array[i]
        result << dna2array[i]
      end
    end


    result.count

  end