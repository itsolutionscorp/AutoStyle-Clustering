require 'date'
require 'time'

class Gigasecond
	def self.from(birthday)
		# birthday + 1e9 / 60 / 60 / 24 # I don't know why this crashes the tests...
		Date.parse( (Time.parse(birthday.to_s) + 1e9).to_s )
	end
end
