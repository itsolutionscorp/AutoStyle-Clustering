class Integer

  RN = {1000=>"M", 500=>"D", 100=>"C", 50=>"L", 10=>"X", 5=>"V", 1=>"I"}
  SPECIAL_RN = {4 => "IV", 9 => "IX", 40 => "XL", 90 => "XC", 400 => "CD", 900 => "CM"}

  def to_roman
    output = ''
    arr = multiply_numbers(self)
    arr.each do |num|
      if special_cases(num)
        output += SPECIAL_RN[num]
      else
        output += build_roman(num)
      end
    end
    output
  end

  private

  def multiply_numbers(num)
    arr = num.to_s.split('')
    zeros = arr.count - 1
    arr.map {|n| z = "0" * zeros; zeros -= 1; n = "#{n}#{z}"; n.to_i}
  end

  def build_roman(int)
    str = ''
    remain = int
    while remain > 0
      small = smallest_numeral(remain)
      str += RN[small]
      remain -= small
    end
    str
  end

  def smallest_numeral(int)
    small = RN.keys.select {|x| int >= x}
    small.first
  end

  def special_cases(int)
    [4,9,40,90,400,900].include?(int)
  end
end
