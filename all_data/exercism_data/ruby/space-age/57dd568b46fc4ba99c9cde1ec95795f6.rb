class SpaceAge
  attr_reader :seconds
  
  def initialize seconds
    @seconds = seconds
  end

  {
    :earth => 1,
    :mercury => 0.2408467,
    :venus => 0.61519726,
    :mars => 1.8808158,
    :jupiter => 11.862615,
    :saturn => 29.447498,
    :uranus => 84.016846,
    :neptune => 164.79132
  }.each do |planet, relative_speed| 
    define_method("on_#{planet}") { (age_on_earth / relative_speed).round(2) }
  end

  def age_on_earth
    (@seconds / 31_557_600.0)
  end
end
