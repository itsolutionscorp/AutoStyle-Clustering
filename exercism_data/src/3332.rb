class Hamming
  def compute code1, code2
    diff = 0
    if code1.length > code2.length then
      code1 = code1[0,code2.length]
    end
    code1.split("").each_with_index do |x,i|
      if code2[i] != x then
        diff += 1
      end
    end
    return diff
  end
end
