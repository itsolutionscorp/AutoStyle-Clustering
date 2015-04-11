class Raindrops
  def self.convert(number)
    words = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

    string = words.select { |k, v| (number % k).zero? }.values.join

    string.empty? ? number.to_s : string
  end
end
