require 'pry'

module PlanetaryTimeCalculations
  def self.included base
    base.extend ClassMethods
  end
  
  module ClassMethods
    # Adds a planet to set that the class knows about.
    # Takes the `name` of the planet as the first arguments and an options
    # hash as the second.  Options can include:
    #   - seconds_in_one_year: defines how many seconds are in one year on
    #   the specified planet
    #   - ratio_to_#{planet}: defines the ratio of number of seconds to
    #   the planet specified.  For example if earth is already defined you
    #   could use ratio_to_earth: <number> as a valid argument
    # This method then creates two new methods:
    #   - .seconds_in_one_#{planet}_year: defines how many seconds are in
    #   a year on the given planet
    #   - #age_on_planet: defines how old the current instance of
    #   space_age would be in years on that planet
    #
    # An example:
    #
    # planet 'earth', seconds_in_one_year: 31_536_000
    # 
    # Creates the methods .seconds_in_one_earth_year and #age_on_earth
    def planet(name, options={})
      seconds_in_one_year = options.fetch(:second_in_one_year) do
        ratio_key = options.keys.find do |keys|
          keys.match(/ratio_to_[a-z]*/)
        end
        raise "planet #{name} must define seconds_in_one_year or ratio_to_planet" if ratio_key.nil?
        planet = ratio_key.to_s.match( /to_([a-z]+)/ )[1]
        seconds_on_planet = send("seconds_in_one_#{planet}_year")
        ratio = options[ratio_key]
        (ratio * seconds_on_planet)
      end

      define_method("on_#{name.downcase}") do
        seconds.fdiv(self.class.send("seconds_in_one_#{name.downcase}_year")).round(3)
      end

      define_singleton_method("seconds_in_one_#{name.downcase}_year") do
        seconds_in_one_year
      end

      @planets ||= []
      @planets << name
    end
  end
end

class SpaceAge
  include PlanetaryTimeCalculations

  planet 'earth',   second_in_one_year: 31_536_000.0
  planet 'mercury', ratio_to_earth: 0.2408467
  planet 'venus',   ratio_to_earth: 0.61519726
  planet 'mars',    ratio_to_earth: 1.8808158
  planet 'jupiter', ratio_to_earth: 11.862615
  planet 'saturn',  ratio_to_earth: 29.447498
  planet 'uranus',  ratio_to_earth: 84.016846
  planet 'neptune', ratio_to_earth: 164.79132

  attr_reader :seconds
  def initialize(age_in_seconds)
    @seconds = age_in_seconds
  end
end
