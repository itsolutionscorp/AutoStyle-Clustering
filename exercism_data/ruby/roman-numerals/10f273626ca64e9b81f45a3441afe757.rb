# Roman numerals exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

class Integer
  def to_roman
    digits = {
      1 => 'I',
      4 => 'IV',
      5 => 'V',
      9 => 'IX',
      10 => 'X',
      40 => 'XL',
      50 => 'L',
      90 => 'XC',
      100 => 'C',
      400 => 'CD',
      500 => 'D',
      900 => 'CM',
      1000 => 'M'
    }
    digit_index = [1,4,5,9,10,40,50,90,100,400,500,900,1000]
    num = self

    result = ""
    pointer = digit_index.length

    while(pointer > -1)
      next if digit_index[pointer-=1] > num
      # find the largest digit that is less than the number
      # subtract off the max multiple of that number
      mult, num = num.divmod(digit_index[pointer])
      result += digits[digit_index[pointer]] * mult
    end
    result
  end
end
