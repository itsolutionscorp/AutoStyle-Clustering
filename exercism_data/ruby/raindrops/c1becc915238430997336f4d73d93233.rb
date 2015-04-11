class Raindrops
  def self.convert(number)
    magic_words = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

    string = ''
    string << magic_words[3] if number % 3 == 0
    string << magic_words[5] if number % 5 == 0
    string << magic_words[7] if number % 7 == 0

    string.empty? ? number.to_s : string
  end
end
