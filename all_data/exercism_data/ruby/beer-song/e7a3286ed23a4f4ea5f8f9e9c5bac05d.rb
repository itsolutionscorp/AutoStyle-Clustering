class Beer

  def sing(starting, ending = 0)
    Song.new(starting, ending).compose
  end

  def verse(bottles_of_beer)
    Song.new(bottles_of_beer, bottles_of_beer).compose.chomp
  end
end


class Song
  attr_accessor :current_bottles
  attr_reader :ending_bottles

  def initialize(starting_bottle, ending_bottles)
    @current_bottles = starting_bottle
    @ending_bottles = ending_bottles
  end
  
  def compose
    verse_collector = ""
    until current_bottles == ending_bottles-1
      verse_collector << compose_verse
      self.current_bottles -= 1
    end
    verse_collector
  end
end
  
def compose_verse
  verse + "\n"
end

def verse
  return no_more_verse if current_bottles == 0
  return song if current_bottles > 0
end

def plurality(num)
  return "#{num} bottles of beer" if num > 1
  return "#{num} bottle of beer" if num == 1
  return "no more bottles of beer" if num == 0
end

def no_more_verse
  "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
end

def song
  num = "one" if current_bottles > 1
  num = 'it' if current_bottles == 1

  plurality(current_bottles).capitalize + " on the wall, " + plurality(current_bottles) + ".\nTake " + num + " down and pass it around, " + plurality(current_bottles-1) + " on the wall.\n"
end
