class Integer
  def to_roman
    data = [
      ["M"  , 1000],
      ["CM" , 900],
      ["D"  , 500],
      ["CD" , 400],
      ["C"  , 100],
      ["XC" ,  90],
      ["L"  ,  50],
      ["XL" ,  40],
      ["X"  ,  10],
      ["IX" ,   9],
      ["V"  ,   5],
      ["IV" ,   4],
      ["I"  ,   1]
    ]

    n = self
    data.map do |k, v|
      a = n / v
      n -= v * a

      (a > 0) ? k * a : ""
    end.join

  end
end
