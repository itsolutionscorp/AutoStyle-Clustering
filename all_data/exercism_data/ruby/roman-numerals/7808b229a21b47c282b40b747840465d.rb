class Fixnum
  def to_roman(number = self)

    roman = ''
    # roman << 'V' *(number % 10/5) && number =- 5 if number % 10/5
    # roman << 'IV'*(number % 8/4) && number =- 4 if number % 8/4
    roman << 'I' *(number % 5/1)
    return roman

  end
end
