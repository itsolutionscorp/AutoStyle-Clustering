#Takes a number and scans the prime factors for single-digit numbers
#  Then returns a sound if it contains 3,5, and/or 7. Else returns number

require 'prime'

class Raindrops
  def self.convert(numIn)
	sound = ''

	convertedNum = Prime.prime_division(numIn).to_s.scan(/\b[2-9]\b/)

	if numIn == 1
		return '1'
	end

	sound += 'Pling' if convertedNum.include? '3'
	sound += 'Plang' if convertedNum.include? '5'
	sound += 'Plong' if convertedNum.include? '7'

	sound = numIn.to_s if sound.empty?

	sound

  end
end
