# Include this module in Fixnum to be able to display numbers 
# up to 3999 ==> MMMCMXCIX

# If you want to represent larger numbers (e.g. up to 39,999), 
# insert two new symbols for each additional power of ten 
# (after the two empty string placeholders) and increment DIGITS

module Roman
  DIGITS = 4
  def to_roman
    if self >= 4 * 10 ** (DIGITS-1)
      raise ArgumentError, 'Number is too large - symbols not defined'
    end
    symbols = ['', '', 'M', 'D', 'C', 'L', 'X', 'V', 'I']
    coeffs = get_coeffs
    result = ''
    DIGITS.times do |i|
      # append a subresult for a coefficient and a 3-element slice of symbols
      result << coefficient_string(coeffs[i], symbols[2*i...2*i+3])
    end
    result
  end

  private

    # coefficients of powers of 10 up to thousands
    def get_coeffs
      ("%0#{DIGITS}d" % [self]).chars.map(&:to_i)
    end

    def coefficient_string(coeff, symbols)
      ten, five, one = *symbols
      result = ''
      if coeff == 10 - 1
        result << (one + ten)
      elsif coeff >= 5
        result << five << (one * (coeff - 5))
      elsif coeff == 5 - 1
        result << (one + five)
      else
        result << (one * coeff)
      end
    end
end

class Fixnum
  include Roman
end
