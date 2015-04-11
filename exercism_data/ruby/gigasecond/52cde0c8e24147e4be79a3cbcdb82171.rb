class Gigasecond
  def self.from(start_date)
    begin
			start_date + (10**9)
		rescue
			puts "Not a date"
		end
	end
end
