require 'date'

class Gigasecond
	def self.from(birthday)
		birthday + 1e9.to_i / 60 / 60 / 24
	end
end
