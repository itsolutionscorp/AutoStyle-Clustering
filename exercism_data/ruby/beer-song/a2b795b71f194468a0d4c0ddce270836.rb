class Beer

  def sing(starting, ending = 0)
    Song.new(starting, ending).compose
  end

  def verse(bottles_of_beer)
    sing(bottles_of_beer, bottles_of_beer).chomp
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
    starting_bottles.downto(ending_bottles).reduce("") do |lyrics, n|
      lyrics << verse(n) +"\n"
    end
  end

  def plurality(number)
    return "#{number} bottles of beer" if number > 1
    return "#{number} bottle of beer" if number == 1
    return "no more bottles of beer" if number == 0
  end

  def subject(current_bottles)
    current_bottles > 1 ? "one" : "it"
  end

  def verse(current_bottles)
    line1(current_bottles) + line2(current_bottles)
  end

  def line1(current_bottles)
    on_the_wall(current_bottles).capitalize + ", " + plurality(current_bottles)
  end

  def line2(current_bottles)
    current_bottles == 0 ?
      ".\nGo to the store and buy some more, " + on_the_wall(99) + ".\n" :
      ".\nTake " + subject(current_bottles) + " down and pass it around, " + on_the_wall(current_bottles-1) + ".\n"
  end

  def on_the_wall(current_bottles)
    plurality(current_bottles) + " on the wall"
  end
end
