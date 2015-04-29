class BeerSong
  def verse(bottle_count)
    BottleVerse.new(bottle_count).to_s
  end

  def verses(start, finish)
    start.downto(finish).each_with_object(""){|bottle_count, song_verse| "#{song_verse}#{self.verse(bottle_count)}\n"}
  end

  def sing
    verses(99, 0)
  end
end

class BottleVerse
  attr_reader :number_of_bottles, :present_bottles, :action_to_take, :remaining_bottles

  BOTTLE_COUNT_TO_PHRASES_MAPPING = Hash.new {|mapping, number| mapping[number] = ["#{number} bottles", "take one down and pass it around", "#{number - 1} bottles"] }
  BOTTLE_COUNT_TO_PHRASES_MAPPING[2] = ['2 bottles', 'take one down and pass it around', '1 bottle']
  BOTTLE_COUNT_TO_PHRASES_MAPPING[1] = ['1 bottle', 'take it down and pass it around', 'no more bottles']
  BOTTLE_COUNT_TO_PHRASES_MAPPING[0] = ['no more bottles', 'go to the store and buy some more', '99 bottles']


  def initialize(number_of_bottles)
    @present_bottles, @action_to_take, @remaining_bottles = BOTTLE_COUNT_TO_PHRASES_MAPPING[number_of_bottles]
  end

  def to_s
    "#{present_bottles.capitalize} of beer on the wall, #{present_bottles} of beer.\n#{action_to_take.capitalize}, #{remaining_bottles} of beer on the wall.\n"
  end
end
