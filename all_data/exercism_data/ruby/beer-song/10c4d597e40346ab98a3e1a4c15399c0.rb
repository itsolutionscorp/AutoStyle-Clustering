class BeerSong
  def verse(start)
    return go_to_the_store if start == 0
    first_line = "#{start} bottles of beer on the wall, #{start} bottles of beer.\n"
    take_one_down(first_line, start)
  end

  def verses(starting_line, finish_line)
    starting_line.downto(finish_line).map { |line| verse(line) }.join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end

  private

  def go_to_the_store
      buy_some_more = "No more bottles of beer on the wall, no more bottles of beer.\n"
      buy_some_more << "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def take_one_down(first_line, start)
    start > 1 ? more_bottles(first_line, start) : no_more_bottles(first_line)
  end

  def more_bottles(first_line, start)
      second_line = "Take one down and pass it around, #{start-1} bottles of beer on the wall.\n"
      (start-1) == 1 ? first_line << single_bottle(second_line) : first_line << second_line
  end

  def no_more_bottles(first_line)
      single_bottle(first_line)
      first_line << "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def single_bottle(line)
    line.gsub!(/bottles/, 'bottle')
  end
end
