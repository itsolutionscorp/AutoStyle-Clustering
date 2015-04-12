class Hamming
  def compute(a1,a2)
    res = 0
    if a1 == a2
      res
    else 
      a1.each do |value,index|
        if a1[index] != a2[index]
          res = res + 1
        end
      end
      res
    end 
  end
end
