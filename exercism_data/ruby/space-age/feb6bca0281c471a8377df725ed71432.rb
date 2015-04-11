class SpaceAge
  attr_reader :seconds

  ORBITAL_PERIODS = {
    earth: 31557600.0,
    mercury: 7600544.0,
    venus: 19414149.0,
    mars: 59354033.0,
    jupiter: 374355659.0,
    saturn: 929307574.0,
    uranus: 2651370019.0,
    neptune: 5200418560.0
  }

  def initialize seconds
    @seconds = seconds
  end
  
  ORBITAL_PERIODS.each do |planet, period|
    define_method("on_#{planet}") { (seconds/period).round(2) }
  end
end
