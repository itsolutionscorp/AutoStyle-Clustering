class Raindrops
  def self.convert(i)
    result = ''
    result += 'Pling' if i % 3 == 0
    result += 'Plang' if i % 5 == 0
    result += 'Plong' if i % 7 == 0
    result = i.to_s if result == ''
    result
  end
end
