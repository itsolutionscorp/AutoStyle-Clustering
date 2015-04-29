class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
    @earth_seconds = seconds.to_f / 31557600
  end

  def on_earth
    @earth_seconds.round(2)
  end

  method_names = [[:on_mercury, 0.2408467],
                  [:on_venus, 0.61519726],
                  [:on_mars, 1.8808158],
                  [:on_jupiter, 11.862615],
                  [:on_saturn, 29.447498],
                  [:on_uranus, 84.016846],
                  [:on_neptune, 164.79132]]


  method_names.each do |method_name|
    define_method(method_name.first) { calc_years(method_name.last) }
  end

  private

  def calc_years(years)
    (@earth_seconds / years).round(2)
  end
end
