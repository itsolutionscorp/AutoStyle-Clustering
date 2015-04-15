class Raindrops

  MAP = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    output = '' 
    MAP.keys.each { |prime| output << MAP[prime] if number % prime == 0 }
    output.empty? ? number.to_s : output
  end

end
