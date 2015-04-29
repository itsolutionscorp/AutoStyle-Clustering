class Hamming

  def self.compute(first_chain, second_chain)		
    hamming_distance = 0

    first_chain.chars.zip(second_chain.chars) do |x, y|
      hamming_distance += 1 if (x && y && (x != y))      
    end

    return hamming_distance
  end
  
end
