class House
  KEY = {
    'house that Jack built' => 'lay in',
    'malt' =>  "ate",
    'rat' =>  "killed",
    'cat' =>  "worried",
    'dog' =>  "tossed",
    'cow with the crumpled horn' => "milked",
    'maiden all forlorn' => 'kissed',
    'man all tattered and torn' => 'married',
    'priest all shaven and shorn' => 'woke',
    'rooster that crowed in the morn' => 'kept',
    'farmer sowing his corn' => 'belonged to',
    'horse and the hound and the horn' => ''
  }

  @@position = 0

  def self.recite
    song = []
    KEY.each do
      song << verse
      @@position += 1
    end
    song.join("\n\n")
  end

  def self.verse 
    part = []
    @@position.times do |i|
      part << "that #{KEY.values[i]} the #{KEY.keys[i]}"
    end
    part << "This is the #{KEY.keys[@@position]}"
    song = part.reverse.join("\n")
    song + "."
  end
end

puts House.recite
