#params [Number]
#return [String]

class Raindrops
  def self.convert(n)
    resulting_string = ''

    resulting_string += 'Pling' if n % 3 === 0
    resulting_string += 'Plang' if n % 5 === 0
    resulting_string += 'Plong' if n % 7 === 0
    (resulting_string.empty? ? "#{n}" : resulting_string)
  end
end
