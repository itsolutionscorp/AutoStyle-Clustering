# roman.rb

module RomanNumbers

  # DEC_TO_ROMAN = {
  #      1 => 'I',
  #      5 => 'V',
  #     10 => 'X',
  #     50 => 'L',
  #    100 => 'C',
  #    500 => 'D',
  #   1000 => 'M',
  # }

  def to_roman
    n, s = self, ''

    s << 'M' * (n/1000)
    n %= 1000
    s << 'C' * (n/100)
    n %= 100
    s << 'X' * (n/10)
    n %= 10
    s << 'I' * n

    s.gsub! /[C]{9}[X]{9}[I]{9}/, 'IM'
    s.gsub! /[C]{9}[X]{9}/, 'XM'
    s.gsub! /[X]{9}[I]{9}/, 'IC'
    s.gsub! /[C]{9}/, 'CM'
    s.gsub! /[X]{9}/, 'XC'
    s.gsub! /[I]{9}/, 'IX'
    s.gsub! /[C]{5}/, 'D'
    s.gsub! /[X]{5}/, 'L'
    s.gsub! /[I]{5}/, 'V'
    s.gsub! /[C]{4}/, 'CD'
    s.gsub! /[X]{4}/, 'XL'
    s.gsub! /[I]{4}/, 'IV'

    s
  end
end

Fixnum.send :include, RomanNumbers
