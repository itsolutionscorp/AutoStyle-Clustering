class SpaceAge
  EARTH_SECONDS = 31557600

  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end

  {:earth => 1,
   :mercury => 0.2408467,
   :venus => 0.61519726,
   :mars => 1.8808158,
   :jupiter => 11.862615,
   :saturn => 29.447498,
   :uranus => 84.016846,
   :neptune => 164.79132
  }.each_pair do |planet, period|
    define_method("on_#{planet}") do
      (@seconds / (EARTH_SECONDS * period).to_f).round 2
    end
  end
end
