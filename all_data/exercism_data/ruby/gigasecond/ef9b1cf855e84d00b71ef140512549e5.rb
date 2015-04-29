class Gigasecond
	def Gigasecond.from(date)
		if date.class.to_s == "Date"
			date+=11574
		elsif date.class.to_s == "Time"
			date+=1000000000
			date = Date.parse(date.asctime)
		end			
	end
end
