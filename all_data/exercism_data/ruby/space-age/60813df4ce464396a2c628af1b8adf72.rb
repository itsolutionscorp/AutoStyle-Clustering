class SpaceAge

	def initialize(seconds)
		@seconds = seconds
		@earth_years = @seconds/31557600.00
	end

	def seconds
		@seconds
	end

	def on_earth
		years = '%.2f' % @earth_years
		years.to_f
	end

	def on_mercury
		years = '%.2f' % (@earth_years/0.2408467)
		years.to_f
	end

	def on_venus
		years = '%.2f' % (@earth_years/0.61519726)
		years.to_f
	end

	def on_mars
		years = '%.2f' % (@earth_years/1.8808158)
		years.to_f
	end

	def on_jupiter
		years = '%.2f' % (@earth_years/11.862615)
		years.to_f
	end

	def on_saturn
		years = '%.2f' % (@earth_years/29.447498)
		years.to_f
	end

	def on_uranus
		years = '%.2f' % (@earth_years/84.016846)
		years.to_f
	end

	def on_neptune
		years = '%.2f' % (@earth_years/164.79132)
		years.to_f
	end
end
