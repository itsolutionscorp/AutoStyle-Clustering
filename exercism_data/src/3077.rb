class Hamming
  def compute(string_a, string_b)
    stra = string_a.split("")
    strb = string_b.split("")
    diff = 0
    for i in 0..stra.length
      if stra[i] != strb[i]
        diff += 1
      end
    end
    diff
  end
end
