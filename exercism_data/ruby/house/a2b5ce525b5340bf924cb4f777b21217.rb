class House

  def self.recite
    0.upto(LYRICS.count-1).map { |v| self.verse(v) }.join("\n")
  end

  def self.verse(v)
    start = "This is the #{LYRICS.values[v]}\n"
    rest = (v-1).downto(0).map do |l|
      "that #{LYRICS.keys[l]} the #{LYRICS.values[l]}\n"
    end
    start + rest.join
  end

  LYRICS = {
    'lay in' => 'house that Jack built.',
    'ate' => 'malt',
    'killed' => 'rat',
    'worried' => 'cat',
    'tossed' => 'dog',
    'milked' => 'cow with the crumpled horn',
    'kissed' => 'maiden all forlorn',
    'married' => 'man all tattered and torn',
    'woke' => 'priest all shaven and shorn',
    'kept' => 'rooster that crowed in the morn',
    'belonged to' => 'farmer sowing his corn',
    'null' => 'horse and the hound and the horn'
  }

end
