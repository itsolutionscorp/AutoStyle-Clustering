class Beer
  def verse(number)
    method = :"verse_#{number}"
    respond_to?(method, true) ? send(method) : verse_n(number)
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map { |number| verse(number) + "\n" }.join
  end

  private

  def verse_0
    "No more bottles of beer on the wall, no more bottles of beer.\n" +
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def verse_1
    "1 bottle of beer on the wall, 1 bottle of beer.\n" +
    "Take it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def verse_n(number)
    count, next_count = number, number.pred
    "#{pluralize count} of beer on the wall, #{pluralize count} of beer.\n" +
    "Take one down and pass it around, #{pluralize next_count} of beer on the wall.\n"
  end

  def pluralize(count)
    count == 1 ? "1 bottle" : "#{count} bottles"
  end
end
