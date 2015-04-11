class Year < Fixnum

	def self.leap? arg 
		@year = arg
		case 
		when @year%4 == 0 
			if @year%100 == 0 
				affirmation if @year%400 == 0
			else
				denial
			end
		when @year%100 == 0
			denial
		end
	end

	def self.affirmation
		"Yes #{@year} is a leap year"
	end

	def self.denial
		"No #{@year} is not leap year"
	end

end
