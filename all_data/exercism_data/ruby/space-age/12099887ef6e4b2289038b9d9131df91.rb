class SpaceAge
    attr_reader :seconds
    SecondsInEarthYear = 31557600.0
    @@seconds_in_year = {
        "earth" => SecondsInEarthYear,
        "mercury" => 0.2408467 * SecondsInEarthYear,
        "venus" => 0.61519726 * SecondsInEarthYear, 
        "mars" => 1.8808158 * SecondsInEarthYear,
        "jupiter" => 11.862615 * SecondsInEarthYear,
        "saturn" => 29.447498 * SecondsInEarthYear,
        "uranus" => 84.016846 * SecondsInEarthYear,
        "neptune" => 164.79132 * SecondsInEarthYear,
    }

    def initialize(seconds)
        @seconds = seconds
    end

    @@seconds_in_year.keys.each do |planet|
        define_method ("on_" + planet).to_sym do
            (@seconds / @@seconds_in_year[planet]).round(2)
        end
    end

end
