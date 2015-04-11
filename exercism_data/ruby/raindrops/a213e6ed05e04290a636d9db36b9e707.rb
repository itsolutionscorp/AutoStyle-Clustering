class Raindrops
  RAINDROP_SPEAK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert num
    factors = RAINDROP_SPEAK.keys.select { |n| (num % n).zero? }
    return factors.inject('') { |ret,key| ret << RAINDROP_SPEAK[key] } unless factors.size == 0
    return num.to_s
  end
end
