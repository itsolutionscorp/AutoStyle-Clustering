module Roman
  def to_roman
    result = ''
    m, c, x, i = coeffs
    result << subresult(m, 'M', '', '')
    result << subresult(c, 'C', 'D', 'M')
    result << subresult(x, 'X', 'L', 'C')
    result << subresult(i, 'I', 'V', 'X')
  end

  private

    # coefficients of powers of 10 up to thousands
    def coeffs
      ('%04d' % [self]).chars.map(&:to_i)
    end

    def subresult(count, one_symbol, five_symbol, ten_symbol)
      result = ''
      if count == 9
        result << (one_symbol + ten_symbol)
      elsif count >= 5
        result << five_symbol << (one_symbol * (count-5))
      elsif count == 4
        result << (one_symbol + five_symbol)
      else
        result << (one_symbol * count)
      end
    end
end

class Fixnum
  include Roman
end
