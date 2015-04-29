class SpaceAge
  attr_reader :seconds

  YEAR_LENGTH_IN_SECONDS = {mercury: 31557600.0 * 0.2408467 , 
                            venus: 31557600.0 * 0.61519726, 
                            earth: 31557600.0,
                            mars: 31557600.0 * 1.8808158, 
                            jupiter: 31557600.0 * 11.862615, 
                            saturn: 31557600.0 * 29.447498,
                            uranus: 31557600.0 * 84.016846, 
                            neptune: 31557600.0 * 164.79132}

  def initialize(seconds)
    @seconds = seconds
  end

  YEAR_LENGTH_IN_SECONDS.each do |planet, ylis|
    define_method "on_#{planet}".to_sym do
      (@seconds / ylis).round(2)
    end
  end
end
