class Hamming

  def self.compute(str1, str2)
    size1=str1.length
    size2=str2.length
    total = ''
    if size1 > size2
      (0..size2-1).each do |i|
        if str1[i] != str2[i]
          total << 1.to_s
        end
      end
    else
      (0..size1-1).each do |i|
        if str1[i] != str2[i]
          total << 1.to_s
        end
      end
    end
    total.count("1")
  end
end
