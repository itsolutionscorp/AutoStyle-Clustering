class Fixnum
  def convert(a,b,c, num)
    conversion = {"0" => "",
                "1" => a,
                "2" => a*2,
                "3" => a*3,
                "4" => a + b,
                "5" => b,
                "6" => b + a ,
                "7" => b + a*2,
                "8" => b + a*3,
                "9" => a + c}
     conversion[num]
  end

  def to_roman
    num = self.to_s.reverse
    [convert("M","","",num[3]),
    convert("C", "D", "M",num[2]),
    convert("X", "L", "C",num[1]),
    convert("I", "V", "X",num[0])].join
  end

end
