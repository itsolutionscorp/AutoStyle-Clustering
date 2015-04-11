class Luhn
	def initialize(number)
		@number = number
	end

	def addends
		array = @number.to_s.split(//).map{|x| x.to_i }.reverse
		result = array.each_with_index.map do |elem, index|
			index.odd? ? elem*2 : elem
		end
		result.map! {|x| x > 10 ? x - 9 : x}.reverse
	end

	def checksum
		self.addends.inject(:+)
	end

	def valid?
		self.checksum % 10 == 0 ? true : false
	end

	def self.create(number)
		str = number.to_s
		(0..9).to_a.each do |x|
			candidate = (str + x.to_s).to_i
			return candidate if Luhn.new(candidate).valid?
		end
	end
end
