class SpaceAge

	EARTH_YEAR = 31_557_600

	attr_reader :seconds 

	def initialize(seconds)
		@seconds = seconds
	end

	@@ratios = {
								on_earth:   1,
								on_mercury: 0.2408467,
								on_venus:   0.61519726,
								on_mars:    1.8808158,
								on_jupiter: 11.862615,
								on_saturn:  29.447498,
								on_uranus:  84.016846,
								on_neptune: 164.79132
             }

	private
		def to_year(ratio)  
			((@seconds / (ratio * EARTH_YEAR).to_f) * 100).round / 100.0
		end

	  def method_missing(method, *args, &block) 
			return to_year(@@ratios[method]) if @@ratios[method] 
			super
		end	
end
