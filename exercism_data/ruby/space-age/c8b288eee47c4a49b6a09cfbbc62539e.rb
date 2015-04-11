class SpaceAge
	def initialize(age_in_seconds)
		@age_in_seconds = age_in_seconds
	end

	EARTH_YEAR = 365.25

	def seconds
		@age_in_seconds
	end

	def on_earth
		determine_years
	end

	def on_mercury
		determine_years 0.2408467
	end

	def on_venus
		determine_years 0.61519726
	end

	def on_mars
		determine_years 1.8808158
	end

	def on_jupiter
		determine_years 11.862615
	end

	def on_saturn
		determine_years 29.447498
	end

	def on_uranus
		determine_years 84.016846
	end

	def on_neptune
		determine_years 164.79132
	end

	private

	def determine_years(earth_years = 1)
		(@age_in_seconds/(60*60*24*EARTH_YEAR*earth_years)).round(2)
	end

end
