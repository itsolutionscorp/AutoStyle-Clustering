class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds

    calclulate_age_on_each_plates
  end

  private

  def calclulate_age_on_each_plates
    base = 31557600.0
    planets = {
      earth:    base,
      mercury:  base * 0.2408467,
      venus:    base * 0.61519726,
      mars:     base * 1.8808158,
      jupiter:  base * 11.862615,
      saturn:   base * 29.447498,
      uranus:   base * 84.016846,
      neptune:  base * 164.79132
    }

    planets.each do |key, value|
      self.class.send(:define_method, "on_#{key.to_s}") { (@seconds / value).round(2) }
    end
  end
end
