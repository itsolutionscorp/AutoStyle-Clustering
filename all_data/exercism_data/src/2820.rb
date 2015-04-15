def compute(n1,n2)
      counter = 0
      n1.split(//).each_index do |x|
        if n1[x] != n2[x]
           counter+=1
        end
    end
    counter
  end