def compute(a,b)
    count = 0
    (0..a.length-1).each do |i|
      count +=1 unless a[i].eql? b[i] or b[i] == nil
    end
    return count
  end