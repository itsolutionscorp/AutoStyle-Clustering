def compute(s1, s2)
    return 0 if s1.eql? s2
    
    distance = 0

    s1.split(//).each_with_index do |c, i|
      distance += 1 unless c.eql? s2.split(//)[i]
    end

    distance
  end