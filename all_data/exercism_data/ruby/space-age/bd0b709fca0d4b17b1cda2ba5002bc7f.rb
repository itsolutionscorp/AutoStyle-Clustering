class SpaceAge

	attr_accessor :seconds
	
	@@EARTH_SECONDS = 31557600.0
	@@yearConversions = {
		"earth" 	=> 1,
		"mercury" 	=> 0.2408467,
		"venus" 	=> 0.61519726,
		"mars" 		=> 1.8808158,
		"jupiter" 	=> 11.862615,
		"saturn" 	=> 29.447498,
		"uranus" 	=> 84.016846,
		"neptune" 	=> 164.79132
	}

	def initialize(seconds)
		@seconds = seconds
	end

	def method_missing(symbol, *arguments, &block)
		if symbol.to_s =~ /^on_(.*)$/ && @@yearConversions[$1]
			on($1)
		else
			super
		end
	end

	def on(planet)
		(years / @@yearConversions[planet]).round(2)
	end

	def years
		seconds / @@EARTH_SECONDS
	end

end
