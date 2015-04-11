class SpaceAge
  class << self
    def include_planets *planets
      planets.each do |planet|
        period =
          case planet
          when :earth   then 1
          when :mercury then 0.2408467
          when :venus   then 0.61519726
          when :mars    then 1.8808158
          when :jupiter then 11.862615
          when :saturn  then 29.447498
          when :uranus  then 84.016846
          when :neptune then 164.79132
          else raise ArgumentError, "don't know the planet: #{planet.to_s}"
          end
        method_name = "on_#{planet}"
        define_method(method_name.to_sym) do |settings = { precise: 2 }|
          (@seconds / 31557600.0 / period).round(settings[:precise])
        end
      end
    end
  end

  include_planets :earth, :mercury, :venus, :mars, :jupiter, :saturn, :uranus, :neptune

  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end
end
