class Fixnum
  def to_roman
    thousands, hundreds, tens, ones = get_digits
    result = ''
    result << parse_thousands(thousands) + parse_hundreds(hundreds) +
    parse_tens(tens) + parse_ones(ones)
    result
  end

  private

  def get_digits
    n = self
    w, n = n / 1000, n % 1000
    x, n = n / 100, n % 100
    y, n = n / 10, n % 10
    z = n
    [w, x, y, z]
  end

  def parse_thousands(n)
    n < 1 ? '' : 'M' * n
  end

  def parse_hundreds(n)
    return '' if n < 1
    return 'C' * n if n <= 3
    return 'CD' if n == 4
    return 'D' + 'C' * (n - 5) if n <= 8
    return 'CM' if n == 9
  end

  def parse_tens(n)
    return '' if n < 1
    return 'X' * n if n <= 3
    return 'XL' if n == 4
    return 'L' + 'X' * (n - 5) if n <= 8
    return 'XC' if n == 9
  end

  def parse_ones(n)
    return '' if n < 1
    return 'I' * n if n <= 3
    return 'IV' if n == 4
    return 'V' + 'I' * (n - 5) if n <= 8
    return 'IX' if n == 9
  end
end
