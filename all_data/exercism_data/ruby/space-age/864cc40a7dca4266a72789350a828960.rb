class SpaceAge
  attr_reader :seconds
  private
  attr_writer :seconds
  EARTH_SECONDS=31557600
  def planets
    {
      earth:   1,
      mercury: 0.2408467,
      venus:   0.61519726,
      mars:    1.8808158,
      jupiter: 11.862615,
      saturn:  29.447498,
      uranus:  84.016846,
      neptune: 164.79132
    }
  end

  def initialize(val)
    self.seconds=val
  end

  def method_missing(method_id,*args,&block)
    if method_id.match(/^on_(.*)$/) && planets.keys.include?($1.intern)
      ("%0.2f" % (seconds.to_f/(planets[$1.intern]*EARTH_SECONDS))).to_f
    else
      super
    end
  end
end
