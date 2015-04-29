class	Gigasecond

	def self.from(beginningDate)
	  @gs = beginningDate.to_datetime + Rational(1000000001 - (1000000000 % 86400), 86400)
      
      return @gs.to_date
	end
end
