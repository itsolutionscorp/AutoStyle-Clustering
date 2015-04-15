class Integer

  ROMAN_KEYS = [{ num: 1000, str: 'M' }, { num: 900, str: 'CM' }, { num: 500, str: 'D' }, { num: 400, str: 'CD' }, { num: 100, str: 'C' }, { num: 90, str: 'XC' }, { num: 50, str: 'L' }, { num: 40, str: 'XL' }, { num: 10, str: 'X' }, { num: 9, str: 'IX' }, { num: 5, str: 'V' }, { num: 4, str: 'IV' }, { num: 1, str: 'I' }]

  def to_roman
    num = self
    str = ''

    ROMAN_KEYS.each do |key|
      while num >= key[:num]
        num -= key[:num]
        str += key[:str]
      end
    end

    str
  end

end
