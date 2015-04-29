class SpaceAge

  orbital_periods_in_seconds = {
    mercury: 7600543.81992,
    venus: 19414149.052176,
    earth: 31557600.0,
    mars: 59354032.69008,
    jupiter: 374355659.124,
    saturn: 929292362.8848,
    uranus: 2651370019.3296,
    neptune: 5200418560.032
  }

  orbital_periods_in_seconds.each do |planet, seconds_per_year|
    define_method("on_#{planet}") do
      (seconds / seconds_per_year).round(2)
    end
  end

  def initialize(seconds)
    @seconds = seconds
  end

  attr_reader :seconds

end
