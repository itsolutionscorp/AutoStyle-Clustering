def compute(string1,string2)
    counter = 0
    string1.split('').each_with_index do |char,index|
      return counter if index >= string2.length
      counter += 1 unless string1[index] == string2[index]
    end
    counter
  end