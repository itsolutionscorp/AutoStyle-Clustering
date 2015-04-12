class Hamming
  def compute(str1, str2)

    ary1 = str1.split("")
    ary2 = str2.split("")
    
    hamming_number = 0
    count = 0

    ary1.each do |acid|
      if acid != ary2[count] && ary2[count]
        hamming_number += 1
      end
      count += 1
    end

    hamming_number
  end
end
