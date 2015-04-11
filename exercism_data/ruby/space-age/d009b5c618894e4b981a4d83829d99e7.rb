class SpaceAge

	PLANETS = {
		Earth: 31557600.0,
   		Mercury: 0.2408467 * 31557600.0,
   		Venus: 0.61519726 * 31557600.0,
   		Mars: 1.8808158 * 31557600.0,
   		Jupiter: 11.862615 * 31557600.0,
   		Saturn: 29.447498 * 31557600.0,
		Uranus: 84.016846 * 31557600.0,
  		Neptune: 164.79132 * 31557600.0
	}

	def initialize(seconds)
		@age = seconds
	end

	def seconds
		@age
	end

	def on_earth
		(@age / PLANETS[:Earth]).round(2)
	end

	def on_mercury
		(@age / PLANETS[:Mercury]).round(2)
	end

	def on_venus
		(@age / PLANETS[:Venus]).round(2)
	end

	def on_mars
		(@age / PLANETS[:Mars]).round(2)
	end

	def on_jupiter
		(@age / PLANETS[:Jupiter]).round(2)
	end

	def on_saturn
		(@age / PLANETS[:Saturn]).round(2)
	end

	def on_uranus
		(@age / PLANETS[:Uranus]).round(2)
	end	

	def on_neptune
		(@age / PLANETS[:Neptune]).round(2)
	end

end
