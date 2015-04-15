class Hamming
  def self.compute(dna1,dna2)
    
    #setup to ignore trailing extra chars
    if dna1.length > dna2.length 
      dna1,dna2 = dna2,dna1
    end
    
    #split into arrays for easy comparing
    arr_dna1 = dna1.split('')
    arr_dna2 = dna2.split('')
    
    #compare each letter in the array, count the differences
    count = 0
    arr_dna1.each_with_index do |letter,i|
       if letter != arr_dna2[i]
         count += 1
       end
    end
    
    count
  end
end
