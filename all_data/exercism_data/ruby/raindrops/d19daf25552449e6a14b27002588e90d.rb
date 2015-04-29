class Raindrops
  @@i2c = {3 => 'i', 5 => 'a', 7 => 'o'}

  def self.convert(number)
    str = @@i2c.map{|k, v| "Pl#{v}ng" if number % k == 0 }.join
    str.empty? ? number.to_s : str
  end
end
