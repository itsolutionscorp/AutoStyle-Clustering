class Hamming

  def self.compute(first_chain, second_chain)
    first_chain.chars.zip(second_chain.chars).reduce(0) do |sum, (x, y)|      
      break sum unless x && y

      if x != y
        sum + 1
      else 
      	sum
      end 
         
    end    
  end  

end
