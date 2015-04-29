class SpaceAge
	SEC_PER_YEAR = 31557600.0

	def initialize(age_in_sec)
		@age_in_earth_years = age_in_sec/SEC_PER_YEAR
	end

	def seconds
		(@age_in_earth_years*SEC_PER_YEAR).round
	end

	def on_earth
		@age_in_earth_years.round(2)
	end

	PERIOD_IN_EARTH_YEARS = {
		mercury:   0.2408467,
		venus:     0.61519726,
		mars:      1.8808158,
		jupiter:  11.862615,
		saturn:   29.447498,
		uranus:   84.016846,
		neptune: 164.79132,
	}
	PERIOD_IN_EARTH_YEARS.each do |planet, period|
		define_method("on_#{planet}") do
			(@age_in_earth_years/period).round(2)
		end
	end
end
