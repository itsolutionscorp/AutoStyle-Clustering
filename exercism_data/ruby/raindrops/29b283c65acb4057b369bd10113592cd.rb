class Raindrops
  def self.convert number
    word = ''
    word << 'Pling' if number % 3 == 0
    word << 'Plang' if number % 5 == 0
    word << 'Plong' if number % 7 == 0
    word = number.to_s if word.empty?
    word
  end
end
