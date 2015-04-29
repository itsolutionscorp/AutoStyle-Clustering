class Raindrops
  def self.convert num
    rain_stack = []
    rain_stack << 'Pling' if num % 3 == 0
    rain_stack << 'Plang' if num % 5 == 0
    rain_stack << 'Plong' if num % 7 == 0
    rain_stack.any? ? rain_stack.join : num.to_s
  end
end
