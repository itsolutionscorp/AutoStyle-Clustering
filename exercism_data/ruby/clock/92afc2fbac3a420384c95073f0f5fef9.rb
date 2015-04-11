class Clock

    def self.at(hour, minute=0)
		hour*60 + minute
	end

	def self.string(num)
		minutes = num % (60*24)
		hours = minutes / 60
		minutes %= 60
		hours = (hours >= 10 ? 1 : 1)
		minutes = (minutes >= 10 ? 1 : 1)
		return "#{hours}:#{minutes}"
	end

end

class Fixnum
    
    def to_s
        Clock.string(self)
    end
    
end
