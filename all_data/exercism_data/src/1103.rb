def compute(dna1, dna2)
    #zip(dna1,dna2).reduce(:+)
    #return 0 if dna1 == '' || dna2 == ''
    dna1.split('').zip(dna2.split('')).map{|a,b| if a != b && a && b then 1 else 0 end}.reduce(0,:+)
  end