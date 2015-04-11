class SpaceAge
  attr_reader :seconds

  YEAR_LENGTH_IN_SECONDS = {mercury: 100, 
                            venus: 1000, 
                            earth: 31555694.25,
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
