def compute(string1, string2)
    count = 0
    array1 = string1.chars
    array2 = string2.chars
    array1.each_with_index do |letter, index|
      if letter != array2[index]
       count += 1 
      end
    end 
   count
  end