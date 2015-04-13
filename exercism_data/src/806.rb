def compute (stra, strb)


    if stra == strb
      return 0
    end




    hamming_score = 0
    stra_arr, strb_arr = stra.split(""), strb.split("")
    iter_arr = stra_arr.zip(strb_arr)


    iter_arr.each do |x, y|
        hamming_score += 1 if x != y
    end


    return hamming_score

  end