class Integer
  def to_roman
    dict = {
      1=> "I",
      4=> "IV",
      5=>'V',
      9=>"IX",
      10=>'X',
      40=>"XL",
      50=>'L',
      90=>'XC',
      100=>'C',
      400=>'CD',
      500=>'D',
      900=>'CM',
      1000=>'M'
    }

    ans = ""
    num = self.to_i
    keys = dict.keys.reverse

    keys.each do |key|
      while num / key > 0
        ans << dict[key]
        num -= key
      end
    end
    ans
  end

end
