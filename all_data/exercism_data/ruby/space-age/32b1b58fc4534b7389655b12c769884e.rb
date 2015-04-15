class SpaceAge
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  private

  def method_missing(method, *args, &block)
    method = method.to_s
    if method.start_with?('on_')
      planet = method[3..-1]
      calculator(planet).in_years
    else
      super
    end
  end

  def calculator(planet)
    begin
      Object.const_get("#{planet.capitalize}AgeCalculator")
    rescue
      EarthAgeCalculator
    end.new(seconds)
  end
end

class EarthAgeCalculator
  attr_reader :seconds

  def initialize(seconds)
    @seconds = seconds
  end

  def in_years
    (seconds / seconds_in_a_year).round(2)
  end

  def seconds_in_a_year
    31557600.0
  end
end

class MercuryAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 0.2408467
  end
end

class VenusAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 0.61519726
  end
end

class MarsAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 1.8808158
  end
end

class JupiterAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 11.862615
  end
end

class SaturnAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 29.447498
  end
end

class UranusAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 84.016846
  end
end

class NeptuneAgeCalculator < EarthAgeCalculator
  def seconds_in_a_year
    super * 164.79132
  end
end
