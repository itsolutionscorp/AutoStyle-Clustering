#
# Convert a number between 0 and 3999 to its equivalent Roman numeral.
#
class Fixnum

  # To convert e.g. 1024: ROMANS[3][1] => 'M', ROMANS[2][0] => '',
  # ROMANS[1][2] => 'XX', and ROMANS[0][4] => 'IV', so 1024 is MXXIV
  ROMANS = [
    [ '', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX' ],
    [ '', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC' ],
    [ '', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM' ],
    [ '', 'M', 'MM', 'MMM' ]
  ]

  # Use .reverse to avoid things like 'decimal = i + (4 - length)'.
  def to_roman
    self                                    # e.g. 1024
      .to_s                                 # '1024'
      .chars                                # ['1', '0', '2', '4']
      .map(&:to_i)                          # [1, 0, 2, 4]
      .reverse                              # [4, 2, 0, 1]
      .map.with_index do |digit, decimal|   # |4,0| |2,1| |0,2| |1,3|
        ROMANS[decimal][digit]              # ROMANS[0][4] => 'IV', etc.
      end                                   # ['IV', 'XX', '', 'M']
      .reverse                              # ['M', '', 'XX', 'IV']
      .join                                 # 'MXXIV'
  end

end
