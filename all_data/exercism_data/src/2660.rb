def compute(dna_one,dna_two)
    smallest = dna_one > dna_two ? dna_two.size - 1 : dna_one.size - 1
    total = 0
    0.upto(smallest) do |i|
      total +=1 if dna_one[i] != dna_two[i]      
    end
    total
  end