class Hamming
  def self.compute str1, str2, opts={}
    ignore_case = opts[:ignore_case]
    distance = 0
    str1 = str1.to_s
    str2 = str2.to_s

    if ignore_case
      str1.downcase!
      str2.downcase!
    end

    if str1.length > str2.length
      distance = 1
    elsif str2.length > str1.length
      distance = 2
    else
      str1.chars.zip(str2.chars).each {|a, b| distance += 1 if a != b }
    end
    return distance
  end
end
