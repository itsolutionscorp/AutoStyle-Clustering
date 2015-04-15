class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  period_factors = { earth:   1.0, 
                     mercury: 0.2408467, 
                     venus:   0.61519726, 
                     mars:    1.8808158,
                     jupiter: 11.862615, 
                     saturn:  29.447498, 
                     uranus:  84.016846, 
                     neptune: 164.79132 }


  period_factors.each do |planet, factor|
    define_method("on_#{planet}".to_sym) { (earth_years / factor).round(2) }
  end

private

  def earth_years
    @earth_years ||= @seconds / (365.25 * 24 * 60 * 60)
  end
end
