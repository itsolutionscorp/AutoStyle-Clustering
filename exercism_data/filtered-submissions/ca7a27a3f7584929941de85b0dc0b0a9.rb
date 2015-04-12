class Hamming

  def compute(a_string, b_string)
    i = 0
    j = 0
    while j < a_string.length
      if a_string[j] == nil || b_string[j] == nil
        i
      elsif a_string[j] != b_string[j]
        i += 1
      end
      j += 1
    end
    i 
  end

end
