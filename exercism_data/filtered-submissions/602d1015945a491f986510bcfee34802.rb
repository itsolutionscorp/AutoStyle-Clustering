class Hamming
  def compute(strand1, strand2)
    count = 0

    for i in 0..strand1.length do 
      if strand1[i] != strand2[i]
        count += 1
      end
    end
    
    count
  end
end
