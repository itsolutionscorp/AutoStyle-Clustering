require 'bigdecimal'
class SpaceAge
	EARTH_SECONDS = 31557600.0
	HASH = {
		:earth => 1.0,
		:mercury => 0.2408467,
		:venus => 0.61519726,
		:mars => 1.8808158,
		:jupiter => 11.862615,
		:saturn => 29.447498, 
		:uranus => 84.016846, 
		:neptune => 164.79132 
		}
	def initialize(number) 
		@number = number
	end
	
	def seconds 
		@number
	end
	
	def on_earth 
		float = @number/(HASH[:earth]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_mercury 
		float = @number/(HASH[:mercury]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_venus 
		float = @number/(HASH[:venus]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_mars 
		float = @number/(HASH[:mars]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_jupiter 
		float = @number/(HASH[:jupiter]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_saturn
		float = @number/(HASH[:saturn]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_uranus 
		float = @number/(HASH[:uranus]*EARTH_SECONDS)
		float.round(2)
	end
	
	def on_neptune
		float = @number/(HASH[:neptune]*EARTH_SECONDS)
		float.round(2)
	end
end
