class Hamming
  def compute(item_a, item_b)
    # if we are the same length, run the test
    if item_a.length == item_b.length
      
      hammings = item_a.chars.map.with_index 
      { |char_a, index|
        char_a != item_b[index] ? 1 : 0 
      }
      
      hammings.reduce { |hamming_count, item| hamming_count + item } 
   
    end
  end
end