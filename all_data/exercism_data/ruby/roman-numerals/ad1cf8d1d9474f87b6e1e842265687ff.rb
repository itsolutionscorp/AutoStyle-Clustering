class Integer

  RN = {1000=>"M", 900 => "CM", 500=>"D", 400 => "CD", 100=>"C", 90 => "XC", 50=>"L", 40 => "XL", 10=>"X", 9 => "IX", 5=>"V", 4 => "IV", 1=>"I"}

  def to_roman
    result = ''
    remain = self
    RN.each do |int, roman|
      while remain >= int
        result += roman
        remain -= int
      end
    end
    result
  end
end
