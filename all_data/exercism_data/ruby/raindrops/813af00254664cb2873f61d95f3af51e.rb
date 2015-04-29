# raindrops.rb
class Raindrops
  def self.convert(n, drops: { Pling: 3, Plang: 5, Plong: 7 })
    drops.select { |_, i| (n % i).zero? }.keys.join[/.+/] || n.to_s
  end
end
