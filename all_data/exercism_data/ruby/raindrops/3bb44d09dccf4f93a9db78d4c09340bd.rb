class Raindrops
  def self.convert num
    s = ''
    s += 'Pling' if num % 3 == 0
    s += 'Plang' if num % 5 == 0 
    s += 'Plong' if num % 7 == 0

    s.empty? ? num.to_s : s
  end
end
