class Raindrops
  def self.convert(num)
    drops = []
    drops << 'Pling' if num % 3 == 0
    drops << 'Plang' if num % 5 == 0
    drops << 'Plong' if num % 7 == 0

    drops.empty? ? num.to_s : drops.join
  end
end
