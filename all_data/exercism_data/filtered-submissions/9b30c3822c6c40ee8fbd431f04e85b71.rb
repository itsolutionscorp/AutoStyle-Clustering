def compute(left, right)


    if left == right
     return 0
    end

    score = 0
    (0..left.size-1).each { |i|


      if i > right.size-1
        break
      end


      if left[i] != right[i]
        score += 1
      end
    }
    score

  end