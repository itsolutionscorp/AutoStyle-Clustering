class Hamming
  def self.compute(str1, str2)
    if str1 === str2
      return 0
    else
      compare = str1.length <=> str2.length

      if compare == -1
        str2 = str2[0, str1.length]
      elsif compare == 1
        str1 = str1[0, str2.length]
      end

      hamming(str1, str2)
    end
  end

  private

  def self.hamming(a, b)
    a = a.split('')
    b = b.split('')
    c = a.zip(b)
    c.map! { |x| x.first == x.last }
    c.keep_if { |value| value == false }.length
  end
end
