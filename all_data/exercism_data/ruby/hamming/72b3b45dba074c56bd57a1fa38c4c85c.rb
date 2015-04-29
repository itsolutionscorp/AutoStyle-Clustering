class Hamming
  def self.compute(str1, str2)
    str1 = str1.split('')
    str2 = str2.split('')

    y = str1.count
    x = str2.count

    if y != x
      if y <=> x = 1
        y = y - x
        y.times { str2.pop }
      elsif y <=> x = -1
        x = x - y
        x.times { str1.pop }
      end
    end

    difference = 0
    str2.each_with_index do |n, i|
      difference += 1 if n != str1[i]
    end
    difference
  end
end
