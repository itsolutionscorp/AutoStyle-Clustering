class Hamming
  def self.compute(input1, input2)
     
    count = 0

    for i in 0...input1.length
      if input1[i] == input2[i]
        count = count 
      elsif input1[i] != input2[i]
        count += 1
      end
    end

    return count
  end  
end
