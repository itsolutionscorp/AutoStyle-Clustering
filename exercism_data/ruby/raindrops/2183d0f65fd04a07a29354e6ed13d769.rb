class Raindrops
  def self.convert(number)
    translation = ''
    translation << 'Pling' if number % 3 == 0
    translation << 'Plang' if number % 5 == 0
    translation << 'Plong' if number % 7 == 0

    translation.empty? ? number.to_s : translation
  end
end
