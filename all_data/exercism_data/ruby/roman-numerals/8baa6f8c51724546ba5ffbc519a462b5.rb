class Fixnum
 def to_roman
    [[1000, "M"], [900, "CM"], [500, "D"], [400, "CD"], [100, "C"], [90, "XC"], [50,"L"], [40,"XL"], [10,"X"], [9,"IX"], [5,"V"], [4,"IV"], [1,"I"]].each do |n|
      return n[1] + (self - n[0]).to_roman if self >= n[0]
    end
    return ""
 end
end
