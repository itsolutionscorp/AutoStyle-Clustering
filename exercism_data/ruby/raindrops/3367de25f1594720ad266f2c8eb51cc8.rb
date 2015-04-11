class Raindrops
  def self.convert number
    speak = ''
    speak << 'Pling'if number % 3 == 0
    speak << 'Plang'if number % 5 == 0
    speak << 'Plong'if number % 7 == 0
    speak << number.to_s if speak.empty?
    speak
  end
end
