class Hamming
  def self.compute(str1, str2)
    case str1.length <=> str2.length
    when -1
      str2 = str2[0, str1.length]
    when 1
      str1 = str1[0, str2.length]
    end

    str1.chars.zip(str2.chars).keep_if { |x| x.first != x.last}.length
  end
end
