class Raindrops
  def self.convert(number)
    Raindrops.new(number,[
      ModuloConverter.new(3, 'Pling'),
      ModuloConverter.new(5, 'Plang'),
      ModuloConverter.new(7, 'Plong')
    ]).convert
  end

  attr_reader :number

  def initialize(number, converters)
    @number = number
    @converters = converters
  end

  def convert
    output = ''

    @converters.each do |converter|
      output << converter.convert(@number)
    end

    output = number.to_s if output.empty?
    output
  end
end

class ModuloConverter
  def initialize(modulo, output)
    @modulo = modulo
    @output = output
  end

  def convert(number)
    if number.modulo(@modulo).zero?
      @output
    else
      ''
    end
  end
end
