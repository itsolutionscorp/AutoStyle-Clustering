class Raindrops

  def self.convert (num)
    rain_str = ''
    rain_str << 'Pling' if num % 3 == 0
    rain_str << 'Plang' if num % 5 == 0
    rain_str << 'Plong' if num % 7 == 0
    rain_str = num.to_s if rain_str.empty?
    return rain_str
  end

end
