class Integer
  INT_TO_ROMAN = {1000 => "M",
                  900  => "CM",
                  500  => "D",
                  400  => "CD",
                  100  => "C",
                  90   => "XC",
                  50   => "L",
                  40   => "XL",
                  10   => "X",
                  9    => 'IX',
                  5    => 'V',
                  4    => 'IV',
                  1    => 'I'}

  def to_roman
    return "" if self.zero?
    divisor, letter = INT_TO_ROMAN.find {|k, v| self / k > 0}
    letter_count, remainder = self.divmod(divisor)
    letter * letter_count + remainder.to_roman
  end
end
