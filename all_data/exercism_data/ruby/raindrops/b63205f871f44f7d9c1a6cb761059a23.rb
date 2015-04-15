class Raindrops
	attr_reader :number

	def self.convert(number)
		self.new(number).convert
	end

	def initialize(number)
		@number = number
	end

	def convert	
		response or number.to_s
	end

	private
	def response
		translate unless translate.empty?
	end

	def translate
		pling + plang + plong
	end

	def pling
		("Pling" if div_by_3?).to_s
	end

	def plang
		("Plang" if div_by_5?).to_s
	end

	def plong
		("Plong" if div_by_7?).to_s
	end

	def div_by_3?
		number % 3 == 0
	end

	def div_by_5?
		number % 5 == 0
	end

	def div_by_7?
		number % 7 == 0
	end
end
