require 'byebug'
class Fixnum
  ROMAN_CONSTANTS = {
      1 => 'I',
      5 => 'V',
     10 => 'X',
     50 => 'L',
    100 => 'C',
    500 => 'D',
   1000 => 'M'   
  }
  def to_roman
    tester = self

    case 
    when tester == 0
      return ''
    when tester >= 1000
      return  Fixnum::ROMAN_CONSTANTS[1000] + (tester - 1000).to_roman
    when tester >= 900  
      return (Fixnum::ROMAN_CONSTANTS[100] + Fixnum::ROMAN_CONSTANTS[1000]) + (tester - 900).to_roman
    when tester >= 500
      return (Fixnum::ROMAN_CONSTANTS[500]) + (tester - 500).to_roman
    when tester >= 400
      return (Fixnum::ROMAN_CONSTANTS[100] + Fixnum::ROMAN_CONSTANTS[500]) + (tester - 400).to_roman
    when tester >= 100
      return Fixnum::ROMAN_CONSTANTS[100] + (tester - 100).to_roman
    when tester >= 90
      return (Fixnum::ROMAN_CONSTANTS[10] + Fixnum::ROMAN_CONSTANTS[100]) + (tester - 90).to_roman
    when tester >= 50
      return Fixnum::ROMAN_CONSTANTS[50] + (tester - 50).to_roman
    when tester >= 40
      return (Fixnum::ROMAN_CONSTANTS[10] + Fixnum::ROMAN_CONSTANTS[50]) + (tester - 40).to_roman
    when tester >= 10
      return Fixnum::ROMAN_CONSTANTS[10] + (tester - 10).to_roman
    when tester >= 9
      return (Fixnum::ROMAN_CONSTANTS[1] + Fixnum::ROMAN_CONSTANTS[10]) + (tester - 9).to_roman
    when tester >= 5
      return Fixnum::ROMAN_CONSTANTS[5] + (tester - 5).to_roman
    when tester >= 4
      return (Fixnum::ROMAN_CONSTANTS[1] + Fixnum::ROMAN_CONSTANTS[5]) + (tester - 4).to_roman
    when tester >= 1
      return Fixnum::ROMAN_CONSTANTS[1] + (tester - 1).to_roman
    end
  end
end
