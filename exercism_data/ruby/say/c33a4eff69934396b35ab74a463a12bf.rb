class Say
  @@ones = [nil, "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eightteen", "nineteen"]
  @@tens = [nil, nil, "twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"]
  @@exps = [nil, nil, "hundred", "thousand", nil, nil, "million",
            nil, nil, "billion", nil, nil, "trillion"]
  def initialize (str)
    @val = str.to_i
    raise ArgumentError if @val < 0 or 999999999999 < @val
  end
  def in_english
    if @val == 0
      "zero"
    else
      [9, 6, 3, 0].map{|i|
        num = hundreds((@val / 10**i) % 10**3)
        num.size > 0 ? "#{num}#{i>0?" ":""}#{@@exps[i]}" : nil
      }.reject(&:nil?).join(" ")
    end
  end
  def hundreds (n)
    h, t, o = n / 100, (n % 100) / 10, n % 10
    res = (h >= 1 ? ["#{@@ones[h]} hundred"] : [])
    if t < 2
      res << @@ones[t * 10 + o]
    elsif o == 0
      res << @@tens[t]
    else
      res << "#{@@tens[t]}-#{@@ones[o]}"
    end
    res.reject(&:nil?).join(" ")
  end
end
