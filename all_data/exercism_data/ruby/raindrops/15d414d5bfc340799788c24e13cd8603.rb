class Raindrops
	def self.convert(num)
		# See if number is divisable by primes 3, 5 and 7 without a remainder.
		# If the number isn't divisable, return it.
    returnString = ''
    returnString += "Pling" if num % 3 == 0
    returnString += "Plang" if num % 5 == 0
    returnString += "Plong" if num % 7 == 0
    returnString.empty? ? num.to_s : returnString
  end
end
