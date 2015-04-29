class Hamming
  def self.compute(str1, str2)
    difference = 0
    if str1.length - str2.length == 0
      difference = calc_difference(str1,str2)
    else
      mod_str = ""
      if str1.length > str2.length
        mod_str << chomp_longer_str(str1, str1.length - str2.length)
        difference = calc_difference(mod_str,str2)
      else
        mod_str << chomp_longer_str(str2, str2.length - str1.length)
        difference = calc_difference(str1,mod_str)
      end
    end
    difference
  end
  
  def self.chomp_longer_str(str, num_extra_chars)
    num_extra_chars.times do str.chop! end
    str
  end
  
  def self.calc_difference(str1, str2)
    difference = 0
    str1.each_char.with_index do |char,index|
      if char != str2[index]
        difference += 1
      end
    end
    difference
  end
end
