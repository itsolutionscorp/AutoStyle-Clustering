class SpaceAge
  YEAR_LENGTHS_IN_SECONDS = {
    mercury:     7_600_544,
    venus:      19_414_149,
    earth:      31_557_600,
    mars:       59_354_033,
    jupiter:   374_355_659,
    saturn:    929_292_363,
    uranus:  2_651_370_019,
    neptune: 5_200_418_560,
  }

  attr_reader :seconds

  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end

  YEAR_LENGTHS_IN_SECONDS .each do |name, year_length|
    define_method("on_#{name}") do
      (seconds / year_length.to_f).round(2)
    end
  end
end
