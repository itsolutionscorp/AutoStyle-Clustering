class Raindrops
  def self.convert(number)
    hash = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

    string = ''
    string << hash[3] if number % 3 == 0
    string << hash[5] if number % 5 == 0
    string << hash[7] if number % 7 == 0

    return string unless string.empty?
    number.to_s
  end
end
