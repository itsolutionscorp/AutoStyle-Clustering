class Hamming
  def compute(a,b)
    errorcount = 0
    testlength = 0
     
    testlength = a.length > b.length ? b.length : a.length

    testlength.times.each{|index|
      errorcount += 1 if a[index] != b[index]
    }
    return errorcount
  end
end
