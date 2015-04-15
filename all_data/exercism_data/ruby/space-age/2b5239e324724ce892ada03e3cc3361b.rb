class SpaceAge
  ORBITAL_TIME = {
    mercury: 7600543,
    venus: 19414149,
    earth: 31557600,
    mars: 59354032,
    jupiter: 374355659,
    saturn: 929292363,
    uranus: 2651370019,
    neptune: 5200418560
  }

  attr_reader :seconds
  def initialize(seconds)
    @seconds = seconds
  end

  ORBITAL_TIME.each do |planet, constant|
    define_method("on_#{planet}") do
      (seconds / constant.to_f).round(2)
    end
  end
end
