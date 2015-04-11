class SpaceAge
  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  def method_missing(meth, *args)
    match_data = meth.to_s.match(/on_(?<planet>.*)/)
    planet = match_data[:planet]
    return super unless planet && AGES.key?(planet)
    age_on(planet)
  end

  def age_on(planet)
    (@seconds / AGES[planet]).round(2)
  end

  AGES = {
    'earth'   => 31557600.0,
    'mercury' => 7600543.81992,
    'venus'   => 19414149.052176,
    'mars'    => 59354032.690079994,
    'jupiter' => 374355659.124,
    'saturn'  => 929292362.8848,
    'uranus'  => 2651370019.3296,
    'neptune' => 5200418560.032001
  }
end
