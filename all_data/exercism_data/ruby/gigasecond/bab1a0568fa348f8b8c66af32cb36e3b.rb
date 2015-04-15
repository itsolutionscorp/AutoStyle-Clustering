class Gigasecond
	def self.from(birthday)
		_giga = birthday.to_datetime + Rational(1000000000, 86400)
		return _giga.to_date
	end
end
