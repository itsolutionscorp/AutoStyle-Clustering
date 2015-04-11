class SpaceAge

  EARTH_YR_SECONDS = 31557600.0 	# Earth: orbital period 365.25 Earth days, or 31557600 seconds
  MERCURY_YR_SECONDS = 0.2408467 * EARTH_YR_SECONDS		# Mercury: orbital period 0.2408467 Earth years
  VENUS_YR_SECONDS = 0.61519726 * EARTH_YR_SECONDS   	# Venus: orbital period 0.61519726 Earth years
  MARS_YR_SECONDS = 1.8808158 * EARTH_YR_SECONDS 			# Mars: orbital period 1.8808158 Earth years
  JUPITER_YR_SECONDS = 11.862615 * EARTH_YR_SECONDS 	#Jupiter: orbital period 11.862615 Earth years
  SATURN_YR_SECONDS = 29.447498 * EARTH_YR_SECONDS 		#Saturn: orbital period 29.447498 Earth years
  URANUS_YR_SECONDS = 84.016846 * EARTH_YR_SECONDS 		#Uranus: orbital period 84.016846 Earth years
  NEPTUNE_YR_SECONDS = 164.79132 * EARTH_YR_SECONDS 	#Neptune: orbital period 164.79132 Earth years
  
	def initialize(secs)
    @secs = secs

	  @seconds_per_year = {
  	"on_earth" => EARTH_YR_SECONDS,
  	"on_mercury" => MERCURY_YR_SECONDS,
  	"on_venus" => VENUS_YR_SECONDS, 
		"on_mars" => MARS_YR_SECONDS,
		"on_jupiter" => JUPITER_YR_SECONDS,
		"on_saturn" => SATURN_YR_SECONDS, 
		"on_uranus" => URANUS_YR_SECONDS,
		"on_neptune" => NEPTUNE_YR_SECONDS
		}
	end

	def seconds
		@secs
	end

  def method_missing(planet)
  	super if planet_does_not_exist(planet) 
  	planet_years(planet)
  end

  def planet_does_not_exist(planet)
    seconds_per_year(planet).nil?  
  end

  def seconds_per_year(planet)
  	@seconds_per_year[planet.to_s]
  end

  def planet_years(planet)
   (@secs/seconds_per_year(planet)).round(2)
  end

end
