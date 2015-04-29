class Roman
  def convert(num)
    number_array = num.to_s.split('')
    roman = []

  ones_place = {
    i: 'I',
    v: 'V',
    x: 'X'}

  tens_place = {
    i: 'X',
    v: 'L',
    x: 'C'}

  hundreds_place = {
    i: 'C',
    v: 'D',
    x: 'M'}

  thousands_place = {
    i: 'M',
    v: 'TOO High!',
    x: 'Waaay too high!'}

    numeral_array = [ones_place, tens_place, hundreds_place, thousands_place]

  number_array.length.times do
    number  = number_array.pop
    numeral = numeral_array.shift

    roman << case number
    when '1' then  "#{numeral[:i]}"
    when '2' then  "#{numeral[:i]}#{numeral[:i]}"
    when '3' then  "#{numeral[:i]}#{numeral[:i]}#{numeral[:i]}"
    when '4' then  "#{numeral[:i]}#{numeral[:v]}"
    when '5' then  "#{numeral[:v]}"
    when '6' then  "#{numeral[:v]}#{numeral[:i]}"
    when '7' then  "#{numeral[:v]}#{numeral[:i]}#{numeral[:i]}"
    when '8' then  "#{numeral[:v]}#{numeral[:i]}#{numeral[:i]}#{numeral[:i]}"
    when '9' then  "#{numeral[:i]}#{numeral[:x]}"
    end
  end

  roman.reverse.join
  end
end

class Fixnum
  def to_roman
    Roman.new.convert(self)
  end
end
