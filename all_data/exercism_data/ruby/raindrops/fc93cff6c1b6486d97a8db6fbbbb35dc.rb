module Raindrops
  def self.convert_multiples(base, text)
    ->(number) { text if number % base == 0 }
  end

  CONVERSIONS = [
    convert_multiples(3, "Pling"),
    convert_multiples(5, "Plang"),
    convert_multiples(7, "Plong"),
  ]

  def self.convert(number)
    CONVERSIONS.reduce('') do |acc, converter|
      acc + converter.call(number).to_s
    end.tap do |translation|
      translation.replace(number.to_s) if translation.empty?
    end
  end
end
