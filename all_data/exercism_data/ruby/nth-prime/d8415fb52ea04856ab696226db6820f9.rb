class Prime
	def self.nth position
		raise ArgumentError if position < 1
		return position + 1 if position <= 2

		foundPrimes = 2
		currentNumber = 4

		until position == foundPrimes do
			currentNumber += 1
			foundPrimes += 1 if self.isPrime?(currentNumber)
		end

		currentNumber
	end

	def self.isPrime?(number)
		return false if number % 2 == 0

		(3..Math.sqrt(number)).step(2).each {|i| return false if (number % i) == 0}
		true
	end
end
