module Raindrops
  KEY_FACTORS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(num)
    drops = KEY_FACTORS.select{|k,v| num % k == 0}.values.join
    return num.to_s if drops.empty?
    drops
  end
end
