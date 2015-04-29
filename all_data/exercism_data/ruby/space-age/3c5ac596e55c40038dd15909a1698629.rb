class SpaceAge
    attr_reader :seconds
    SECONDS_IN_EARTH_YEARS = 31557600.0
    @@seconds_in_year = {
        "earth" => SECONDS_IN_EARTH_YEARS,
        "mercury" => 0.2408467 * SECONDS_IN_EARTH_YEARS,
        "venus" => 0.61519726 * SECONDS_IN_EARTH_YEARS, 
        "mars" => 1.8808158 * SECONDS_IN_EARTH_YEARS,
        "jupiter" => 11.862615 * SECONDS_IN_EARTH_YEARS,
        "saturn" => 29.447498 * SECONDS_IN_EARTH_YEARS,
        "uranus" => 84.016846 * SECONDS_IN_EARTH_YEARS,
        "neptune" => 164.79132 * SECONDS_IN_EARTH_YEARS
    }

    def initialize(seconds)
        @seconds = seconds
    end

    @@seconds_in_year.keys.each do |planet|
        define_method ("on_" + planet).to_sym do
            on planet
        end
    end

    private

    def on(planet)
        (@seconds / @@seconds_in_year[planet]).round(2)
    end

end
