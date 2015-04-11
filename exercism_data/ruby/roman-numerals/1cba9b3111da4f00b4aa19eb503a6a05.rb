class Fixnum
  def to_roman
    # num_to_roman = {
    #   1 => 'I',
    #   4 => 'IV',
    #   5 => 'V',
    #   9 => 'IX',
    #   10 => 'X',
    #   40 => 'XL',
    #   50 => 'L',
    #   90 => 'XC',
    #   100 => 'C',
    #   400 => 'CD',
    #   500 => 'D',
    #   900 => 'CM',
    #   1000 => 'M'
    # }

    num = self
    string = ''
    string << 'M' * (num / 1000)
    num -= (num / 1000) * 1000
    string << 'CM' * (num / 900)
    num -= (num / 900) * 900
    string << 'D' * (num / 500)
    num -= (num / 500) * 500
    string << 'CD' * (num / 400)
    num -= (num / 400) * 400
    string << 'C' * (num / 100)
    num -= (num / 100) * 100
    string << 'XC' * (num / 90)
    num -= (num / 90) * 90
    string << 'L' * (num / 50)
    num -= (num / 50) * 50
    string << 'XL' * (num / 40)
    num -= (num / 40) * 40
    string << 'X' * (num / 10)
    num -= (num / 10) * 10
    string << 'IX' * (num / 9)
    num -= (num / 9) * 9
    string << 'V' * (num / 5)
    num -= (num / 5) * 5
    string << 'IV' * (num / 4)
    num -= (num / 4) * 4
    string << 'I' * (num / 1)
    num -= (num / 1) * 1
    string
  end
end
