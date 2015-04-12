def compute(dna1, dna2)
    result = 0
    dna1.split("").each_with_index{ |item, index| 
      result+=1 if (dna2[index]!=nil && item!=dna2[index])  }
    result
  end