class Fixnum

  ONES_ROMANS = %W[#{''} I II III IV V VI VII VIII IX]
  TENS_ROMANS = %W[#{''} X XX XXX XL L LX LXX LXXX XC]
  HUNDREDS_ROMANS = %W[#{''} C CC CCC CD D DC DCC DCCC CM]
  TOUSANDS_ROMANS = %W[#{''} M MM MMM MMMM MMMMM MMMMMMM]

  def to_roman
    ones = self % 10
    tens = (self / 10) % 10
    hundreds = (self / 100) % 10
    tousands = (self / 1000) % 10
    roman = ''
    roman << TOUSANDS_ROMANS[tousands]
    roman << HUNDREDS_ROMANS[hundreds]
    roman << TENS_ROMANS[tens]
    roman << ONES_ROMANS[ones]
  end

end
