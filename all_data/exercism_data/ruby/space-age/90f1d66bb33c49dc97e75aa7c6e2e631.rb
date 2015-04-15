class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  def method_missing meth, *args, &block
    planet = meth.to_s.split(/_/).last
    (seconds / (ages(planet) * earth)).round 2
  end

  private

  def earth
    31_557_600.to_f
  end

  def ages planet
    {
      'earth'   => 1,
      'mercury' => 0.2408467,
      'venus'   => 0.61519726,
      'mars'    => 1.8808158,
      'jupiter' => 11.862615,
      'saturn'  => 29.447498,
      'uranus'  => 84.016846,
      'neptune' => 164.79132
    }.fetch(planet)
  end
end
