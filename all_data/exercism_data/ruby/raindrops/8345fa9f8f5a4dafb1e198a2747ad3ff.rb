class Raindrops
  def self.convert(n)
    rain = (n % 3 == 0 ? 'Pling' : '') + (n % 5 == 0 ? 'Plang' : '') + (n % 7 == 0 ? 'Plong' : '')
    return n.to_s if rain == '' else rain
  end
end
