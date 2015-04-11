module Roman
  
  def to_roman
    @number = self
    @numeral = ""

    [1000, 500, 100, 50, 10, 5, 1].each do |n|
      div_result = @number.div(n)
      next if div_result.zero?

      @numeral << romanize(n) * div_result
      @number -= n * div_result
    end

    sub(@numeral)
  end

  def sub(numeral)
    numeral.gsub!(/IIII/, 'IV')
    numeral.gsub!(/VIV/, 'IX')
    numeral.gsub!(/XXXX/, 'XL')
    numeral.gsub!(/LXL/, 'XC')
    numeral.gsub!(/CCCC/, 'CD')
    numeral.gsub!(/DCD/, 'CM')
    numeral
  end

  def romanize(n)
    {
      I: 1,
      V: 5,
      X: 10,
      L: 50,
      C: 100,
      D: 500,
      M: 1000
    }.key(n).to_s
  end
end

class Fixnum
  include Roman
end
