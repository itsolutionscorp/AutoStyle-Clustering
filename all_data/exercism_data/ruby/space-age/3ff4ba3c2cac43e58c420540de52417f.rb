class SpaceAge

	def initialize(seconds)
		@seconds = seconds
	end

	def seconds
		@seconds
	end
	
	def on_earth
		earth = (@seconds / 31557600.0).round(2)
	end
	
	def on_planet(relative_to_earth)
		((@seconds / 31557600.0) / relative_to_earth).round(2)
	end
	
	def on_mercury
		self.on_planet(0.2408467)
	end
	
	def on_venus
		self.on_planet(0.61519726)
	end
	
	def on_mars
		self.on_planet(1.8808158)
	end
	
	def on_jupiter
		self.on_planet(11.862615)
	end
	
	def on_saturn
		self.on_planet(29.447498)
	end
	
	def on_uranus
		self.on_planet(84.016846)
	end
	
	def on_neptune
		self.on_planet(164.79132)
	end
	
end
