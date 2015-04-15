class Fixnum
  def to_roman
    num1 = self
    str = ""
    Hash char_hsh = {1000 => 'M', 500 => 'D', 100 => 'C', 50 => 'L', 10 => 'X', 5 => 'V', 1 => 'I'}
    [1000, 500, 100, 50, 10, 5, 1].each_with_object({}) do |n, hsh|
      hsh[n] = num1.divmod(n)
      num1 = hsh[n][1]
    end.each do |k, v|
      str.concat(char_hsh[k] * v[0])
    end
    str.sub("LXXXX","XC").sub("DCCCC","CM").sub("CCCC","CD").sub("XXXX","XL").sub("IIII","IV").sub("VIV","IX")
  end
end
