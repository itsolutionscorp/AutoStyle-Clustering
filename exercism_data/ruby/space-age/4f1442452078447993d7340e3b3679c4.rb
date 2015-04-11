class SpaceAge
	EARTH_SEC = 31557600.0
	def initialize(sec)
		@years = sec/EARTH_SEC
	end
	def seconds
		(@years*EARTH_SEC).round
	end
	def on_earth
		@years.round(2)
	end
	def on_mercury
		(@years/0.2408467).round(2)
	end
	def on_venus
		(@years/0.61519726).round(2)
	end
	def on_mars
		(@years/1.8808158).round(2)
	end
	def on_jupiter
		(@years/11.862615).round(2)
	end
	def on_saturn
		(@years/29.447498).round(2)
	end
	def on_uranus
		(@years/84.016846).round(2)
	end
	def on_neptune
		(@years/164.79132).round(2)
	end
end
