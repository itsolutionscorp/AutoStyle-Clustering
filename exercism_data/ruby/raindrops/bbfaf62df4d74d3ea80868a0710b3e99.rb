class Raindrops

  RAINDROPS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(int)
    translated = []
    factors = (1..int).select { |n| int % n == 0 }
    factors.each { |x| translated << RAINDROPS[x] if RAINDROPS.has_key?(x) }
    return translated.join('') if translated.count > 0
    return int.to_s
  end
end
