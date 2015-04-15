class Raindrops
  def self.convert(n)
    rain = ''
    rain << 'Pling' if n % 3 == 0
    rain << 'Plang' if n % 5 == 0
    rain << 'Plong' if n % 7 == 0
    rain.empty? ? n.to_s : rain
  end
end
