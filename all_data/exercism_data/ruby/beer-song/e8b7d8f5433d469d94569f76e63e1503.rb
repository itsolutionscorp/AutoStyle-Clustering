class BeerSong
  def verse(number)
    Verse.new(number).sing
  end

  def verses(first, last)
    first.downto(last).map { |x| verse x }.join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end
end

Verse = Struct.new(:number) do
  def sing
    "#{number_of_bottles.capitalize} of beer on the wall, " \
    "#{number_of_bottles} of beer.\n" \
    "#{take_whatever_down_or_go_to_the_store}, " \
    "#{next_number_of_bottles} of beer on the wall.\n"
  end

  private

  def number_of_bottles(override=nil)
    case override or number
      when 0 then "no more bottles"
      when 1 then "1 bottle"
      else "#{override or number} bottles"
    end
  end

  def take_whatever_down_or_go_to_the_store
    take_whatever_down or go_to_the_store
  end

  def take_whatever_down
    "Take #{number == 1 ? "it" : "one"} down and pass it around" unless number.zero?
  end

  def go_to_the_store
    "Go to the store and buy some more" # but don't drive
  end

  def next_number_of_bottles
    number_of_bottles (number - 1) % 100 # thanks @thomvil :)
  end
end
