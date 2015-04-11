class Integer
  def to_roman
    case self
    when 0..3 then 'I' * self
    when 4    then 'IV'
    when 5..8 then  'V' + modulo(5).to_roman
    when 9    then  'IX'
    when 10..39 then 'X' * div(10) + modulo(10).to_roman
    when 40..49 then 'XL' + modulo(10).to_roman
    when 50..89 then  'L' + modulo(50).to_roman
    when 90..99 then  'XC' + modulo(10).to_roman
    when 100..399 then 'C' * div(100) + modulo(100).to_roman
    when 400..499 then 'CD' + modulo(100).to_roman
    when 500..899 then  'D' + modulo(500).to_roman
    when 900..999 then  'CM' + modulo(100).to_roman
    when 1000..3999 then 'M' * div(1000) + modulo(1000).to_roman
    end
  end
end
