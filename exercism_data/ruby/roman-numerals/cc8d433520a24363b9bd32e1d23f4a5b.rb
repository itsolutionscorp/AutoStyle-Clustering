module Roman
  def to_roman
    symbols = ['',''] + %w(M D C L X V I)
    coeffs = get_coeffs
    result = ''
    4.times do |i|
      result << subresult(coeffs[i], symbols[2*i...2*i+3])
    end
    result
  end

  private

    # coefficients of powers of 10 up to thousands
    def get_coeffs
      ('%04d' % [self]).chars.map(&:to_i)
    end

    def subresult(count, symbols)
      ten_symbol, five_symbol, one_symbol = *symbols
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
