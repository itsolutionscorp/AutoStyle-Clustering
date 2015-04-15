class Year
	def self.leap? (year)
		divide4 = 0
		divide100 = 0
		divide400 = 0

		if year % 4 == 0
			divide4 = 1
		end

		if year % 100 == 0
			divide100 = 1
		end

		if year % 400 == 0
			divide400 = 1
		end

		status = divide4.to_s + divide100.to_s + divide400.to_s

		return false if status == '000'
		return true if status == '100'
		return false if status == '010'
		return true if status == '001'
		return false if status == '110'
		return true if status == '011'
		return true if status == '111'


	end
end

puts 'Enter a Year'
year = gets.chomp
puts Year.leap? year.to_i
until 'exit' == year do
  year = gets.chomp
  puts Year.leap? year.to_i
end
