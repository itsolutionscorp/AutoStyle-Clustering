class SpaceAge

	attr_accessor :seconds
	
	@@SECONDS_PER_EARTH_YEAR = 31557600.0
	@@yearConversions = {
		earth: 		  1.0,
		mercury: 	  0.2408467,
		venus: 		  0.61519726,
		mars: 	 	  1.8808158,
		jupiter: 	 11.862615,
		saturn: 	 29.447498,
		uranus: 	 84.016846,
		neptune: 	164.79132
	}

	def initialize(seconds)
		@seconds = seconds
	end

	def method_missing(symbol, *arguments, &block)
		if symbol.to_s =~ /^on_(.*)$/ && @@yearConversions[$1.to_sym]
			on($1.to_sym)
		else
			super
		end
	end

	def on(planet)
		(earth_years / @@yearConversions[planet]).round(2)
	end

	private

		def earth_years
			seconds / @@SECONDS_PER_EARTH_YEAR
		end

end
