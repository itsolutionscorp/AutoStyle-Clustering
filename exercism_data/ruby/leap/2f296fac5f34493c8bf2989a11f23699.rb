class Year
	def self.leap?(year)
		(standard?(year) and not_century?(year)) or fourth_century?(year)
	end

  def self.standard?(year)
		(year % 4).eql?(0)
	end

	def self.not_century?(year)
		!(year % 100).eql?(0)
	end

	def self.fourth_century?(year)
		(year % 400).eql?(0)
	end
end
