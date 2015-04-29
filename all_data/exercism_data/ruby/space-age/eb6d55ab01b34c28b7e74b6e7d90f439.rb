class SpaceAge
  YEAR_ON_SEC = 31557600

  ORBITAL_PERIODS = {
                    :earth   => 1          ,
                    :mercury => 0.2408467  ,
                    :venus   => 0.61519726 ,
                    :mars    => 1.8808158  ,
                    :jupiter => 11.862615  ,
                    :saturn  => 29.447498  ,
                    :neptune => 164.79132  ,
                    :uranus  => 84.016846   }

  def initialize seconds
    @seconds = seconds
  end

  def seconds
    @seconds
  end

  def calc period
    @seconds.fdiv(YEAR_ON_SEC * period).round(2)
  end

  ORBITAL_PERIODS.each do |key, value|
    define_method "on_#{key}" do
      calc value
    end
  end

end
