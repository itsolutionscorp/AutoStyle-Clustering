def compute(dna1,dna2)


    if dna1.length > dna2.length
      dna1,dna2 = dna2,dna1
    end


    arr_dna1 = dna1.split('')
    arr_dna2 = dna2.split('')


    count = 0
    arr_dna1.each_with_index do |letter,i|
       if letter != arr_dna2[i]
         count += 1
       end
    end

    return count
  end