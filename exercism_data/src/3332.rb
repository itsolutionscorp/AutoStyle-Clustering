def compute(strA, strB)
      if strA.length > strB.length
        strA, strB = strB, strA
      end
      strA.chars.zip(strB.chars).count { |a, b| a != b }
    end