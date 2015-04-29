module RomanNumeral

	def to_roman
		roman = String.new
		number = self
		length = number.to_s.length

		number.digits.each do |digit|
			place = 0
			case length
			when 4
				place = 1000
			when 3
				place = 100
			when 2
				place = 10
			when 1
				place = 1
			end

			add = ''
			add = roman_helper(digit, place)
			roman += add unless add == '' || add == nil

			length -= 1
		end

		return roman
	end

	def roman_helper(digit, place)
		return '' if digit == 0
		number = digit * place
		if place == 1000
			return "MMM" if number == 3000
			return "MM" if number == 2000
			return "M" if number == 1000
		elsif place == 100
			return "CM" if number == 900
			return "DCCC" if number == 800
			return "DCC" if number == 700
			return "DC" if number == 600
			return "D" if number == 500
			return "CD" if number == 400
			return "CCC" if number == 300
			return "CC" if number == 200
			return "C" if number == 100
		elsif place == 10
			return "XC" if number == 90
			return "LXXX" if number == 80
			return "LXX" if number == 70
			return "LX" if number == 60
			return "L" if number == 50
			return "XL" if number == 40
			return "XXX" if number == 30
			return "XX" if number == 20
			return "X" if number == 10
		elsif place == 1
			return "IX" if number == 9
			return "VIII" if number == 8
			return "VII" if number == 7
			return "VI" if number == 6
			return "V" if number == 5
			return "IV" if number == 4
			return "III" if number == 3
			return "II" if number == 2
			return "I" if number == 1
		end
	end

end

class Integer
	include RomanNumeral

  def digits
    Enumerator.new do |x|
      to_s.chars.map{|c| x << c.to_i }
    end
  end

end
