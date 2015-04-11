module Planets
	def self.included(base)
		base.extend(ClassMethods)
	end
	
	module ClassMethods
		@@planets = { "earth" 	=> 31557600.0,
								"mercury" => 31557600 * 0.2408467,
								"venus"		=> 31557600 * 0.61519726,
								"mars"		=> 31557600 * 1.8808158,
								"jupiter"	=> 31557600 * 11.862615,
								"saturn"	=> 31557600 * 29.447498,
								"uranus"	=> 31557600 * 84.016846,
								"neptune"	=> 31557600 * 164.79132 }
		
		def self.extended(base)
			@@planets.each do |planet, period|
				base.send(:define_method, "on_#{planet}") do
					(@seconds / period).round(2)
				end
			end
		end
	end
end

class SpaceAge
	include Planets
	attr_reader :seconds
	
	def initialize(seconds)
		@seconds = seconds
	end
end
