class Hamming
  def self.compute(str1, str2)
    ary1  = str1.split("");    ary2  = str2.split("");
    dist1 = (ary1-ary2).count; dist2 = (ary2-ary1).count
    # if 2 of args' length are different, return the larger
    return dist1 > dist2 ? dist1 : dist2
  end
end
