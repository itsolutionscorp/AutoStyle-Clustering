class Year
	def self.leap?(year)
		quan, modul = year.divmod(100);
		date = (modul == 0) ? quan : year
		(date % 4 == 0)
	end
end
