class Raindrops
  def self.convert(number)
    sound = ""
    sound << "Pling" if multiple_of_three?(number)
    sound << "Plang" if multiple_of_five?(number)
    sound << "Plong" if multiple_of_seven?(number)
    sound.length == 0 ? number.to_s : sound
  end

  def self.multiple_of_three?(number)
    number % 3 == 0
  end

  def self.multiple_of_five?(number)
    number % 5 == 0
  end

  def self.multiple_of_seven?(number)
    number % 7 == 0
  end
end
