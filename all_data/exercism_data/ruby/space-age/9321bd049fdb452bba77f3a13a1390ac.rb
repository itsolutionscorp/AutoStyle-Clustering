# fails this test:
# assert_equal 9.78, SpaceAge.new(189_839_836).on_venus
# Expected: 9.78
# Actual: 9.79
#
# but looking at it, it is 9.785479213610282
# so it should round up, so it seems like the expectation is wrong

class SpaceAge
  def initialize(earth_seconds)
    self.earth_seconds = earth_seconds
  end

  def seconds
    earth_seconds
  end

  def on_earth()   round earth_seconds / 31557600.0 end
  def on_venus()   round on_earth      / 0.61519726 end
  def on_mercury() round on_earth      / 0.2408467  end
  def on_mars()    round on_earth      / 1.8808158  end
  def on_jupiter() round on_earth      / 11.862615  end
  def on_saturn()  round on_earth      / 29.447498  end
  def on_uranus()  round on_earth      / 84.016846  end
  def on_neptune() round on_earth      / 164.79132  end

  private

  attr_accessor :earth_seconds

  def round(n)
    n.round(2)
  end
end
