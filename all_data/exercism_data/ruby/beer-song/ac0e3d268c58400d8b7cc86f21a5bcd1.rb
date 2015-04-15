class Beer

  def sing(starting, ending = 0)
    Song.new(starting, ending).compose
  end

  def verse(bottles_of_beer)
    Song.new(bottles_of_beer, bottles_of_beer).compose.chomp
  end
end


class Song
  attr_reader :starting_bottles
  attr_reader :ending_bottles

  def initialize(starting_bottles, ending_bottles)
    @starting_bottles = starting_bottles
    @ending_bottles = ending_bottles
  end
  
  def compose
    lyrics = ""
    current_bottles = self.starting_bottles

    until current_bottles == ending_bottles-1
      lyrics << verse(current_bottles) + "\n"
      current_bottles -= 1
    end
    lyrics
  end
end

def plurality(num_bottles)
  return "#{num_bottles} bottles of beer" if num_bottles > 1
  return "#{num_bottles} bottle of beer" if num_bottles == 1
  return "no more bottles of beer" if num_bottles == 0
end

def subject(current_bottles)
  ( "one" if current_bottles > 1 ) || "it"
end

def verse(current_bottles)
  line1(current_bottles) + line2(current_bottles)
end

def line1(current_bottles)
  plurality(current_bottles).capitalize + " on the wall, " + plurality(current_bottles)
end

def line2(current_bottles)
  if current_bottles == 0
    ".\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  else
    ".\nTake " + subject(current_bottles) + " down and pass it around, " + plurality(current_bottles-1) + " on the wall.\n"
  end
end
