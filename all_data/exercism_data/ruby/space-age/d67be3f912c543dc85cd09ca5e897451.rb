class SpaceAge
  attr_reader :seconds

  YEAR_LENGTH_IN_SECONDS = {mercury: 100, 
                            venus: 1000, 
                            earth: 31557600,
                            mars: 2000, 
                            jupiter: 10000, 
                            saturn: 100000,
                            uranus: 2652994592.0, 
                            nepture: 5196280668.0}

  def initialize(seconds)
    @seconds = seconds
  end

  YEAR_LENGTH_IN_SECONDS.each do |planet, ylis|
    define_method "on_#{planet}".to_sym do
      (@seconds / ylis).round(2)
    end
  end
end

# Earth: orbital period 365.25 Earth days, or 31557600 seconds
#    - Mercury: orbital period 0.2408467 Earth years
#    - Venus: orbital period 0.61519726 Earth years
#    - Mars: orbital period 1.8808158 Earth years
#    - Jupiter: orbital period 11.862615 Earth years
#    - Saturn: orbital period 29.447498 Earth years
#    - Uranus: orbital period 84.016846 Earth years
#    - Neptune: orbital period 164.79132 Earth years
