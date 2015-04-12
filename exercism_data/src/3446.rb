class Hamming  
  
  def compute(string1, string2)
    result = []
    char_index = 0
    longer  = string1.length > string2.length ? string1 : string2
    shorter = longer == string1 ? string2 + ("*" * (string1.length - string2.length)) : string1 + ("*" * (string2.length - string1.length))
    longer.each_char do |character|
      result << 1 if character != shorter[char_index]
      char_index += 1      
    end
    result.inject(:+) ? result.inject(:+) : 0
  end

end
