def compute(dna1, dna2)
    result = 0
    dna1.split("").each_with_index{ |item, index|
      if (dna2[index]!=nil && item!=dna2[index]) then result+=1 end }
    result
  end