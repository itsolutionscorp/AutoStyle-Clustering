class SpaceAge
  attr_accessor :seconds

  PLANET_YEAR_IN_SECONDS = {
    earth: 31557600.0,
    mercury: 7600543.81992,
    venus: 19414149.0522,
    mars: 59354032.6901,
    jupiter: 374355659.124,
    saturn: 929292362.885,
    uranus: 2651370019.33,
    neptune: 5200418560.03
  }

  def initialize seconds
    @seconds = seconds
  end

  self::PLANET_YEAR_IN_SECONDS.each_pair do |planet, planetSeconds|
    define_method "on_#{planet.to_s}" do
      (@seconds / planetSeconds).round(2)
    end
  end
end
