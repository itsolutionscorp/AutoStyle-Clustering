# Preconditions
## Assume sequences are strings of equal length

class Hamming
  def compute(dna1,dna2)
    if dna1 == dna2
      return 0
    else
      dna1 = dna1.split("")
      dna2 = dna2.split("")
      
      errors = 0
      
      for i in 0..(dna1.length-1)
         unless dna1[i] == dna2[i]
           errors+=1
         end
      end
      
      return errors      
    end
  end
end
