class Hamming
  def self.compute(code1, code2)
    diff = 0
    if code1.length == code2.length
      for i in 0..code1.length
        if code1[i] != code2[i]
          diff += 1
        end
      end
      return diff
    elsif
      code1.length > code2.length
      return 1
    else
      return 2
    end
  end
end
