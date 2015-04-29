class Raindrops

  def self.convert(num)
    speak(num.to_i).to_s
  end

  def self.speak(number)
    speech = ''
    speech << "Pling" if number % 3 == 0
    speech << "Plang" if number % 5 == 0
    speech << "Plong" if number % 7 == 0
    speech.empty? ? number : speech
  end

end
