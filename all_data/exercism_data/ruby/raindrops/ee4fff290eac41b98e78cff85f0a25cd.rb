class Raindrops
  DROP_SPEAK = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong",
  }

  def self.convert(number)
    drops = ''

    DROP_SPEAK.each do |factor, word|
      drops << word if number%factor == 0
    end

    if drops == ''
      drops = number.to_s
    end

    drops
  end
end
