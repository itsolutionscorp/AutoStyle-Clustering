class Integer

  def to_roman
    num = self
    in_roman = []
    if num > 3999
      print "Romans did not regularly count this high, so I'm told. Thus, you will need to figure this out on your own."
    else
      if num > 999
        in_roman.push thousands[ ( num / 1000 ) * 1000 ]
        num = num - ( num / 1000 ) * 1000 # This removes the thousands place value
      end
      if num > 99
        in_roman.push hundreds[ ( num / 100 ) * 100 ]
        num = num - ( num / 100 ) * 100 # This removes the hundreds place value
      end
      if num > 9
        in_roman.push tens[ ( num / 10 ) * 10 ]
        num = num - ( num / 10 ) * 10 # This removes the tens place value
      end

      in_roman.push ones[ num ]
    end
    in_roman.join()
  end

  def ones
    { 1 => "I", 2 => "II", 3 => "III", 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX" }
  end

  def tens
    { 10 => "X", 20 => "XX", 30 => "XXX", 40 => "XL", 50 => "L", 60 => "LX", 70 => "LXX", 80 => "LXXX", 90 => "XC" }
  end

  def hundreds
    { 100 => "C", 200 => "CC", 300 => "CCC", 400 => "CD", 500 => "D", 600 => "DC", 700 => "DCC", 800 => "DCCC", 900 => "CM" }
  end

  def thousands
    { 1000 => "M", 2000 => "MM", 3000 => "MMM" }
  end

end
