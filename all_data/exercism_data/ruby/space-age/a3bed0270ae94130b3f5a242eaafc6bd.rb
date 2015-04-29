class SpaceAge
  attr_accessor :mercury_years_from, :venus___years_from, :mars____years_from,
                :jupiter_years_from, :saturn__years_from, :uranus__years_from,
                :neptune_years_from
  attr_accessor :seconds

  def initialize(seconds)
    @seconds = seconds.to_f

    @mercury_years_from = lambda { |years| (years / 0.24084670).round(2) }
    @venus___years_from = lambda { |years| (years / 0.61519726).round(2) }
    @mars____years_from = lambda { |years| (years / 1.88081580).round(2) }
    @jupiter_years_from = lambda { |years| (years / 11.8626150).round(2) }
    @saturn__years_from = lambda { |years| (years / 29.4474980).round(2) }
    @uranus__years_from = lambda { |years| (years / 84.0168460).round(2) }
    @neptune_years_from = lambda { |years| (years / 164.791320).round(2) }
  end

  def earth_years () @seconds / 31_557_600                 end

  def on_earth    () earth_years.round(2)                  end

  def on_mercury  () get @mercury_years_from,  earth_years end

  def on_venus    () get @venus___years_from,  earth_years end

  def on_mars     () get @mars____years_from,  earth_years end

  def on_jupiter  () get @jupiter_years_from,  earth_years end

  def on_saturn   () get @saturn__years_from,  earth_years end

  def on_uranus   () get @uranus__years_from,  earth_years end

  def on_neptune  () get @neptune_years_from,  earth_years end

  def get      (a,b) a.call b                              end

end
