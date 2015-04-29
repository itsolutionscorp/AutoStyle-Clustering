class Fixnum


  def to_roman
    roman = []
    convert(roman,self)
  end

  def convert(roman, number)

    case

    when number >= 1000
      roman << 'M'
      convert(roman,number-1000)
    when number >= 900
      roman << 'CM'
      convert(roman,number-900)
    when number >= 500
      roman << 'D'
      convert(roman,number-500)
    when number  >= 400
      roman << 'CD'
      convert(roman,number-400)
    when number >= 100
      roman << 'C'
      convert(roman,number-100)
    when number >= 90
      roman << 'XC'
      convert(roman,number-90)
    when number >= 50
      roman << 'L'
      convert(roman,number-50)
    when number >= 40
      roman << 'XL'
      convert(roman,number-40)
    when number >= 10
      roman << 'X'
      convert(roman,number-10)
    when number >= 9
      roman << 'IX'
      convert(roman,number-9)
    when number >= 5
      roman << 'V'
      convert(roman,number-5)
    when number >= 4
      roman << 'IV'
      convert(roman,number-4)
    when number >= 1
      roman << 'I'
      convert(roman,number-1)
    else
      ' '
    end
    return roman.join
  end
end
