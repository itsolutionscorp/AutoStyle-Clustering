module Raindrops
  SOUND_OF = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert n
    text = SOUND_OF.each_with_object("") { |(div,word),phrase|
      phrase << word if n%div==0
    }
    text.empty? ? n.to_s : text
  end
end
