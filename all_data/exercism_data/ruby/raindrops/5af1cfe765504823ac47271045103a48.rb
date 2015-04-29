class Raindrops
  def self.convert(n)
    value = ""
    value += 'Pling' if n%3 == 0
    value += 'Plang' if n%5 == 0
    value += 'Plong' if n%7 == 0
    return value.empty? ? n.to_s : value
  end
end
