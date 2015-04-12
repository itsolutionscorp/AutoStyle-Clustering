class Hamming
  def compute num1, num2 
    count = 0
    str1= num1.to_s.split('')
    str2= num2.to_s.split('')
    str1.length.times do |i|
        count += 1 if str1[i] != str2[i]
    end
    count
  end
end
