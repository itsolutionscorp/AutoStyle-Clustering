class Hamming
  def compute(code1, code2)
    diff = 0
    if code1.length == code2.length
      for i in 0..code1.length
        if code1[i] != code2[i]
          diff += 1
        end
      end
      return diff
    else
      return code1.length > code2.length ? 1 : 2
    end
  end
end
