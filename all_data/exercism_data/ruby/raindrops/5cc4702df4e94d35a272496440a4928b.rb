class Raindrops
  def self.convert(number)
    convert = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    found = convert.keys.map { |prime| convert[prime] if number % prime == 0 }
    found.compact.empty? ? number.to_s : found.join
  end
end
