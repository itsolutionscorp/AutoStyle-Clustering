class Raindrops
  def self.convert(num)
    pling = num % 3 == 0 ? 'Pling' : nil
    plang = num % 5 == 0 ? 'Plang' : nil
    plong = num % 7 == 0 ? 'Plong' : nil
    pling || plang || plong ? "#{pling}#{plang}#{plong}" : num.to_s
  end
end
