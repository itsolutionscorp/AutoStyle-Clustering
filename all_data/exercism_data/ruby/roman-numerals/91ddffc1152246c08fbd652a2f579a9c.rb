class Fixnum
  def to_roman
    num = self.to_s.reverse
    [thousand(num[3]), hundred(num[2]), ten(num[1]), ones(num[0])].join
  end

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

  def ones(num)
    convert("I", "V", "X", num)
  end

  def ten(num)
    convert("X", "L", "C", num)
  end

  def hundred(num)
    convert("C", "D", "M", num)
  end

  def thousand(num)
    "M"*num.to_i if num != nil
  end
end
