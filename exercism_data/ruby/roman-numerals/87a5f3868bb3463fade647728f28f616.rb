class Fixnum

  def to_roman(n=self)
    case
    when n >= 1000 then 'M' + to_roman(n-1000)
    when n >= 900 then 'CM' + to_roman(n-900)

    when n >= 500 then 'D' + to_roman(n-500)
    when n >= 400 then 'CD' + to_roman(n-400)

    when n >= 100 then 'C' + to_roman(n-100)
    when n >= 90 then 'XC' + to_roman(n-90)

    when n >= 50 then 'L' + to_roman(n-50)
    when n >= 40 then 'XL' + to_roman(n-40)

    when n >= 10 then 'X' + to_roman(n-10)
    when n >= 9 then 'IX'

    when n >= 5 then 'V' + to_roman(n-5)
    when n >= 4 then 'IV'

    else 'I' * n
    end
  end

end
