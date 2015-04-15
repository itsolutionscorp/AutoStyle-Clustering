class SpaceAge
	SECONDS_CONTSANT_MULTIPLE = 24*60*60
	EARTH_DAYS = 365.25
    MERCURY = 0.2408467
    VENUS =  0.61519726
    MARS =  1.8808158
    JUPITER =  11.862615
    SATURN =  29.447498
    URANUS =  84.016846
    NEPTUNE =  164.79132
	def initialize(age_in_seconds)
		@age_in_seconds = age_in_seconds
	end

	def seconds
		@age_in_seconds.to_s.split('_').join.to_i
	end

	def on_earth
		 (@age_in_seconds.to_f/(EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	
	end

	def on_mars
		 (@age_in_seconds.to_f/(MARS * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	
	end

	def on_mercury
		(@age_in_seconds.to_f/(MERCURY * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	end

	def on_venus
		(@age_in_seconds.to_f/(VENUS * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	end

	def on_jupiter
		(@age_in_seconds.to_f/(JUPITER * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	end

	def on_saturn
		(@age_in_seconds.to_f/(SATURN * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	end

	def on_uranus
		(@age_in_seconds.to_f/(URANUS * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	end

	def on_neptune
		(@age_in_seconds.to_f/(NEPTUNE * EARTH_DAYS * SECONDS_CONTSANT_MULTIPLE)).round(2)
	end
end
