class Raindrops
  def self.convert(num)
    string = ''
    string += 'Pling' if num % 3 === 0
    string += 'Plang' if num % 5 === 0
    string += 'Plong' if num % 7 === 0
    string.empty? ? num.to_s : string
  end
end
