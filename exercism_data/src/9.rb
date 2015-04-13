def compute(a,b)



    array_a = a.each_char.to_a
    size_a = array_a.size
    array_b = b.each_char.to_a
    size_b = array_b.size




   min = (size_a > size_b) ? size_b : size_a




    min -= 1
    hamming = 0
    i = 0




    until i > min do
      (array_a[i].eql?(array_b[i])) ? i += 1 : (hamming += 1; i += 1)
    end
    hamming
  end