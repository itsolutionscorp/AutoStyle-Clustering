module Raindrops
  RAINDROP_SPEAK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(input)
    Converter.new(input).raindrops or input.to_s
  end

  class Converter
    attr_reader :input

    def initialize(input)
      @input = input
    end

    def raindrops
      @raindrops ||= RAINDROP_SPEAK.map do |number, string|
        string if input % number == 0
      end.join
      @raindrops unless @raindrops.empty?
    end
  end
end
