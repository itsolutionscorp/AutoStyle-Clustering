class Integer
  def to_roman
    Roman.int_to_roman(self)
  end
end

module Roman
  def self.int_to_roman(integer)
    return if integer <= 0
    roman = ""
    roman += thousands(integer)
    roman += hundreds(integer)
    roman += tens(integer)
    roman += ones(integer)
  end

  class << self
    private

    def thousands(integer)
      digit = (integer % 10000) / 1000
      numeral_map = {
        1 => 'M',
        5 => 'V', # 5000 is just 'V'
      }
      numeral_for(digit, numeral_map, 'I', 'M')
    end

    def hundreds(integer)
      digit = (integer % 1000) / 100
      numeral_map = {
        1 => 'C',
        5 => 'D',
        10 => 'M',
      }
      numeral_for(digit, numeral_map, 'C')
    end

    def tens(integer)
      digit = (integer % 100) / 10
      numeral_map = {
        1 => 'X',
        5 => 'L',
        10 => 'C',
      }
      numeral_for(digit, numeral_map, 'X')
    end

    def ones(integer)
      digit = integer % 10
      numeral_map = {
        1 => 'I',
        5 => 'V',
        10 => 'X',
      }
      numeral_for(digit, numeral_map, 'I')
    end

    def numeral_for(digit, numerals, prefix, postfix=nil)
      postfix ||= prefix
      if digit == 0
        ""
      elsif numerals.keys.include?(digit)
        numerals[digit]
      elsif numerals.keys.map{ |num| one_below?(digit, num) }.any?
        "#{prefix}#{numerals[digit+1]}"
      else
        result, current = "", digit
        while numerals[current].nil?
          result << postfix
          current -= 1
        end
        "#{numerals[current]}#{result}"
      end
    end

    def one_below?(maybe_below, num)
      (num - 1) == maybe_below
    end
  end
end
