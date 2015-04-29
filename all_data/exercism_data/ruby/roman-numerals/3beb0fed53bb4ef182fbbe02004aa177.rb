@romans = {1 => 'I',4 => 'IV',5 => 'V',9 => 'IX',10 => 'X',40 => 'XL',50 => 'L', 90 => 'XC', 100 => 'C',400 => 'CD', 500 => 'D', 900 => 'CM', 1000 => 'M'}

@result = ''
def to_romans(num)
  @romans.sort.reverse.each do |key, roman|
    if num/key == 1
      @result << roman
      to_romans(num%key)
      break
    end
  end
  @result
end
