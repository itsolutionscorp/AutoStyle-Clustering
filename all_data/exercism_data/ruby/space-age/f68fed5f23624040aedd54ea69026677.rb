class SpaceAge
  require 'bigdecimal'
  
  attr_reader :seconds_old
  alias_method :seconds, :seconds_old

  EARTH_YEARS = { earth:     1.0,
                  mercury:   0.2408467,
                  venus:     0.61519726,
                  mars:      1.8808158,
                  jupiter:  11.862615,
                  saturn:   29.447498,
                  uranus:   84.016846,
                  neptune: 164.79132 }
  
  EARTH_ORBIT_YEAR = { days: 365.25, seconds: 31557600 }

  def initialize( age_in_seconds )
    @seconds_old = age_in_seconds
  end

  def method_missing( meth, *args, &block )
    if meth.to_s =~ /on_(.+)/
      age_on_planet( $1, *args, &block )
    elsif meth.to_s =~ /age_on_(.+)/
      age_on_planet( $1, *args, &block )
    else
      super
    end
  end

  def age_on_planet( planet, precision=2 )
    # earth_age = age_on_earth( precision )
    # planet_age = earth_age.div( BigDecimal.new( EARTH_YEARS[ planet.to_sym ] ), precision )
    age = BigDecimal.new( @seconds_old.to_s ).div( orbit_in_seconds( planet ), 20)
    age.round( precision ).to_f
  end

  def orbit_in_seconds( planet )
    BigDecimal.new( EARTH_YEARS[ planet.to_sym ].to_s ).mult( BigDecimal.new( EARTH_ORBIT_YEAR[ :seconds ].to_s ), 20 )
  end
end
