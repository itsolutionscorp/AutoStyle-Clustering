class Hamming
  
  def self.char_pairs str_1, str_2
      string_1, string_2 = [str_1, str_2].sort_by(&:length)
      string_1.chars.zip(string_2.chars)
  end

  def self.compute str1, str2
    char_pairs(str1, str2).count {|x,y| x != y }
  end

end
