class Year

	def self.leap?(val)
	val%4 == 0 && (val%400 == 0 || val%100 !=0)
	end

end
