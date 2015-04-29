class Fixnum

  def to_roman

    thousands, fivehundreds = self.divmod(1000)
    fivehundreds, hundreds = fivehundreds.divmod(500)
    hundreds, fifties = hundreds.divmod(100)
    fifties, tens = fifties.divmod(50)
    tens, fives = tens.divmod(10)
    fives, ones = fives.divmod(5)
    str = ''
    str << 'M' * thousands
    if fivehundreds == 1 && hundreds == 4
      str << 'CM'
    elsif fivehundreds == 0 && hundreds == 4
      str << 'CD'
    else
      str << 'D' * fivehundreds
      str << 'C' * hundreds
    end
    if fifties == 1 && tens == 4
      str << 'XC'
    elsif fifties == 0 && tens == 4
      str << 'XL'
    else
      str << 'L' * fifties
      str << 'X' * tens
    end
    if fives == 1 && ones == 4
      str << 'IX'
    elsif fives == 0 && ones == 4
      str << 'IV'
    else
      str << 'V' * fives
      str << 'I' * ones
    end
  end

end
