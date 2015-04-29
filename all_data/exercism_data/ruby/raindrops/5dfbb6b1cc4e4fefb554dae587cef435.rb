class Raindrops

  @rain_drops = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    rain_drop = ''

    [3, 5, 7].each do |prime|
      rain_drop += @rain_drops[prime] if number % prime == 0
    end

    rain_drop = number.to_s if rain_drop.empty?

    rain_drop
  end

end
