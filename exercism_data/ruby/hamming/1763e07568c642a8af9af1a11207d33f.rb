class Hamming
  def self.compute(str1, str2)
    compare = str1.length <=> str2.length

    if compare == -1
      str2 = str2[0, str1.length]
    elsif compare == 1
      str1 = str1[0, str2.length]
    end

    hamming(str1, str2)
  end

  def self.hamming(a, b)
    a = a.split('')
    b = b.split('')
    c = a.zip(b)
    c.keep_if { |x| x.first != x.last }.length
  end
  
  private_class_method :hamming
end
