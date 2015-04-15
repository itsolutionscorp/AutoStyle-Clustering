#
# Convert a number between 0 and 3999 to its equivalent Roman numeral.
#
class Fixnum

  # To convert e.g. 12 to Roman numerals using the ROMANS array below: look in
  # the ones decimal position (index 0) for the digit 2 (index 2), i.e.
  # ROMANS[0][2], which is 'II', then look in the tens decimal position (index
  # 1) for the digit 1 (index 1), i.e. ROMANS[1][1], which is 'X'.  12 is
  # therefore XII.
  ROMANS = [
    [ '', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX' ],   # ones
    [ '', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC' ],   # tens
    [ '', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM' ],   # hundreds
    [ '', 'M', 'MM', 'MMM' ]                                          # thousands
  ]

  # Use .reverse to avoid thinks like 'decimal = i + (4 - length)'.
  def to_roman
    self                        # e.g. 1024
      .to_s                     # '1024'
      .chars                    # ['1', '0', '2', '4']
      .map(&:to_i)              # [1, 0, 2, 4]
      .reverse                  # [4, 2, 0, 1]
      .each_with_index          # [(4,0), (2,1), (0,2), (1,3)]
      .map do |digit, decimal|  # |4,0| |2,1| |0,2| |1,3|
        ROMANS[decimal][digit]  # ROMANS[0][4] => 'IV', etc.
      end                       # ['IV', 'XX', '', 'M']
      .reverse                  # ['M', '', 'XX', 'IV']
      .join                     # 'MXXIV'
  end

end
