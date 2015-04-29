module Raindrops
  DropWords = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(num)
    numstring = DropWords.map do |drops, word|
      word if num % drops == 0
    end.join

    numstring.empty? ? num.to_s : numstring
  end
end
