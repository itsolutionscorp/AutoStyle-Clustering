class Hamming
  def compute(string_a, string_b)
    stra = string_a.split("")
    strb = string_b.split("")
    diff = 0
    stra.each_with_index do |letter, index|
      diff += 1 if strb[index] != letter
    end
    diff
  end
end
