class Raindrops

  WORDS = ["Pling", "Plang", "Plong"]

  def self.convert(num)
    string = ''
    if (num % 3).zero? then string << WORDS[0] end
    if (num % 5).zero? then string << WORDS[1] end
    if (num % 7).zero? then string << WORDS[2] end
    string.empty? ? num.to_s : string
  end

end
