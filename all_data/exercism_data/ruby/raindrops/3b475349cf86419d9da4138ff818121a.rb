class Raindrops
  class << self
    def convert(number)
      output = ''
      output <<= 'Pling' if number % 3 == 0
      output <<= 'Plang' if number % 5 == 0
      output <<= 'Plong' if number % 7 == 0

      output.empty? ? number.to_s : output
    end
  end
end
