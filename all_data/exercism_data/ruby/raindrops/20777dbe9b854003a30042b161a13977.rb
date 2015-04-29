class Raindrops
  def self.convert(number)
    evaluations = [["Pling", number%3], ["Plang", number%5], ["Plong", number%7]]
    sound = ""
    evaluations.each do |noise, evaluation|
      if evaluation == 0
        sound << noise
      end
    end
    if sound == ""
      return number.to_s
    else
      return sound
    end
  end
end
