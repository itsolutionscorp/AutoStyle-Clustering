class Raindrops

  MAP = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    output = MAP.keys.map { |prime|  MAP[prime] if number % prime == 0 }.join
    output.empty? ? number.to_s : output
  end

end
