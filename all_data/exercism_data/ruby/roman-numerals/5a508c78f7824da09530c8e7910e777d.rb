# This code only runs on ruby 1.9.x due to Fixnum objects being frozen in ruby-2.0 and greater.
# Tests in 'roman_numerals_test.rb' assume ability to directly modify Fixum class.
# But that's not possible in newer versions of ruby at least from what I could find.
# http://stackoverflow.com/questions/15840233/cant-modify-frozen-fixnum-on-ruby-2-0

module Roman
  ROMAN_NUMERAL_1000 = 'M'
  ROMAN_NUMERAL_900 = 'CM' # special numeral used in 900-999, not 'DCCC' or 'DCD'
  ROMAN_NUMERAL_500 = 'D'
  ROMAN_NUMERAL_400 = 'CD' # special numeral used in 400-499, not 'CCCC'
  ROMAN_NUMERAL_100 = 'C'
  ROMAN_NUMERAL_90 = 'XC' # special numeral used in 90-99, not 'LXXXX' or 'LXL'
  ROMAN_NUMERAL_50 = 'L'
  ROMAN_NUMERAL_40 = 'XL' # special numeral used in 40-49, not 'XXXX'
  ROMAN_NUMERAL_10 = 'X'
  ROMAN_NUMERAL_9 = 'IX' # special numeral used for 9, not 'XIIII' or 'VIV'
  ROMAN_NUMERAL_5 = 'V'
  ROMAN_NUMERAL_4 = 'IV' # special numeral used for 4, not 'IIII'
  ROMAN_NUMERAL_1 = 'I'

  def to_roman
    @roman_numeral = ''

    @divisible_by_1000 = self.divmod(1000)
    @divisible_by_500 = (@divisible_by_1000[1]).divmod(500)
    @divisible_by_100 = (@divisible_by_500[1]).divmod(100)
    @divisible_by_50 = (@divisible_by_100[1]).divmod(50)
    @divisible_by_10 = (@divisible_by_50[1]).divmod(10)
    @divisible_by_5 = (@divisible_by_10[1]).divmod(5)
    @divisible_by_1 = (@divisible_by_5[1]).divmod(1)

    @roman_numeral << (ROMAN_NUMERAL_1000 * @divisible_by_1000[0])
    @roman_numeral << (ROMAN_NUMERAL_500 * @divisible_by_500[0])
    @roman_numeral << (ROMAN_NUMERAL_100 * @divisible_by_100[0])
    @roman_numeral << (ROMAN_NUMERAL_50 * @divisible_by_50[0])
    @roman_numeral << (ROMAN_NUMERAL_10 * @divisible_by_10[0])
    @roman_numeral << (ROMAN_NUMERAL_5 * @divisible_by_5[0])
    @roman_numeral << (ROMAN_NUMERAL_1 * @divisible_by_1[0])

    replace_special_roman_numerals(@roman_numeral)
  end

  private

  def replace_special_roman_numerals(str)
    str.sub!('IIII', ROMAN_NUMERAL_4)
    str.sub!('XIIII', ROMAN_NUMERAL_9)
    str.sub!('VIV', ROMAN_NUMERAL_9)
    str.sub!('XXXX', ROMAN_NUMERAL_40)
    str.sub!('LXXXX', ROMAN_NUMERAL_90)
    str.sub!('LXL', ROMAN_NUMERAL_90)
    str.sub!('CCCC', ROMAN_NUMERAL_400)
    str.sub!('DCCCC', ROMAN_NUMERAL_900)
    str.sub!('DCD', ROMAN_NUMERAL_900)
    str
  end
end

class Fixnum
  include Roman
end
