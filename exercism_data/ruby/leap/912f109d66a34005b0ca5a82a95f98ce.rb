class Year
	def self.leap?(yr)
		yr%4==0 && (yr%100!=0 or yr%400==0)
	end	
end
