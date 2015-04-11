class ::Integer
  def to_roman
    substitutions = {1000=>"M",
                     900=>"CM",
                     500=>"D",
                     400=>"CD",
                     100=>"C",
                     90=>"XC",
                     50=>"L",
                     40=>"XL",
                     10=>"X",
                     9=>"IX",
                     5=>"V",
                     4=>"IV",
                     1=>"I"}
    substitutions.reduce(["", self]) do |(result, num), (div, replacement)|
      divisions, n = num.divmod(div)
      [result << replacement*divisions, n]
    end[0]
  end
end
