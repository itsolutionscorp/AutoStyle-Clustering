class Year
	def self.leap?(n)
		return (n % 400 == 0) || ( ( n % 100 !=0 ) && ( n % 4 == 0 ) )
	end
end
