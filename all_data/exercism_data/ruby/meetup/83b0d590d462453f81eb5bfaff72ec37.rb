class Meetup
	def initialize(month,year)
		@month = month
		@year = year
	end

	def monteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.monday?
				return d
			end
	    end
	end
	def tuesteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.tuesday?
				return d
			end
	    end
	end
	def wednesteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.wednesday?
				return d
			end
	    end
	end
	def thursteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.thursday?
				return d
			end
	    end
	end
	def friteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.friday?
				return d
			end
	    end
	end
	def saturteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.saturday?
				return d
			end
	    end
	end
	def sunteenth
		(13..19).each do |date|
			d = Date.new(@year,@month,date)
			if d.sunday?
				return d
			end
	    end
	end

	["monday","tuesday","wednesday","thursday","friday","saturday","sunday"].each do |weekday|
      define_method("first_#{weekday}") do
(1..7).each do |date|
			d = Date.new(@year,@month,date)
			print d.send("#{weekday}?")
			if d.send("#{weekday}?")
				return d
			end
	    end
      end
    end
    ["monday","tuesday","wednesday","thursday","friday","saturday","sunday"].each do |weekday|
      define_method("second_#{weekday}") do
(8..14).each do |date|
			d = Date.new(@year,@month,date)
			print d.send("#{weekday}?")
			if d.send("#{weekday}?")
				return d
			end
	    end
      end
    end
    ["monday","tuesday","wednesday","thursday","friday","saturday","sunday"].each do |weekday|
      define_method("third_#{weekday}") do
(15..21).each do |date|
			d = Date.new(@year,@month,date)
			print d.send("#{weekday}?")
			if d.send("#{weekday}?")
				return d
			end
	    end
      end
    end
    ["monday","tuesday","wednesday","thursday","friday","saturday","sunday"].each do |weekday|
      define_method("fourth_#{weekday}") do
(22..29).each do |date|
			d = Date.new(@year,@month,date)
			print d.send("#{weekday}?")
			if d.send("#{weekday}?")
				return d
			end
	    end
      end
    end
    ["monday","tuesday","wednesday","thursday","friday","saturday","sunday"].each do |weekday|
      define_method("last_#{weekday}") do
      	if @month != 2
(25..31).each do |date|
	        
			  d = Date.new(@year,@month,date)
		    
			print d.send("#{weekday}?")
			if d.send("#{weekday}?")
				return d
			end
		end
	else
	(22..28).each do |date|
	        
			  d = Date.new(@year,@month,date)
		    
			print d.send("#{weekday}?")
			if d.send("#{weekday}?")
				return d
			end
		end	
	   end
      end
    end
end
