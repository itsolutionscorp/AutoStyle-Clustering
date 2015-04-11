class Hamming

  def self.compute(strand1, strand2)
    test_length = [strand1.length, strand2.length].min

    sr1 = strand1.split("") 
    sr2 = strand2.split("") 

    dist = 0
    i = 0
    while i < test_length  do
     if(sr1[i] != sr2[i])
       dist = dist + 1
     end
     i = i + 1
    end

    return dist
  end
end
