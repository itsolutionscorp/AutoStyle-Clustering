class Hamming
  def compute(string1, string2)
    count = 0
    string1.each_char.with_index {|char, index| count += 1 if char != string2[index]}
    count
  end 
end
