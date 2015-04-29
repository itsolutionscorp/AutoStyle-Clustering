class Raindrops
  def self.convert(num)
    drops = []
    drops << "Pling" if num.remainder(3).zero?
    drops << "Plang" if num.remainder(5).zero?
    drops << "Plong" if num.remainder(7).zero?
    drops.any? ? drops.join : num.to_s
  end
end
