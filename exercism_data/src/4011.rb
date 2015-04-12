class Hamming
  def compute(string1, string2)
    diffcount = 0
    if string1.size > string2.size
      string1 = string1[0...string2.length]
    elsif string2.size > string1.size
      string2 = string2[0...string1.length]
    end
    string1.split('').each_with_index do |char, index|
      diffcount += 1 if string2[index] != char
    end
    diffcount
  end
end
