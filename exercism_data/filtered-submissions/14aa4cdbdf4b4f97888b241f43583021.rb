def compute(a,b)
    Hamming.new


    array_a = a.each_char.to_a
    size_a = array_a.size
    array_b = b.each_char.to_a
    size_b = array_b.size



    if size_a > size_b then c = size_b
    else if size_a < size_b then c = size_a else
         c = size_a
         end
    end


    c = c - 1
    hamming = 0
    i = 0



    until i > c do
      if array_a[i].eql?(array_b[i]) then
        i += 1
      else
        hamming += 1
        i += 1

      end
    end
    hamming
  end