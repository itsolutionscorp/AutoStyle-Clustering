class Hamming
  def self.compute (stra, strb)
    # don't waste time comparing if everything is equal!
    # check if strings are a match - return 0
    if stra == strb 
      return 0
    end
    
    # main interator
    # split both inputs into arrays and then zip them together

    hamming_score = 0
    stra_arr, strb_arr = stra.split(""), strb.split("")
    iter_arr = stra_arr.zip(strb_arr)
    
    # compare each pair of letter for a match    
    iter_arr.each do |x, y|
        hamming_score += 1 if x != y
    end
    
    # spit out our result
    return hamming_score
    
  end
end
