class Beer
  def verse(bottles)
    line_one(bottles) + line_two(bottles)
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map { |i| verse(i) + "\n" }.join
  end

  private

  def line_one(bottles)
    bottles = "no more" if bottles == 0
    suffix  = bottles == 1 ? "" : "s"

    "#{bottles} bottle#{suffix} of beer on the wall, " \
    "#{bottles} bottle#{suffix} of beer.\n".capitalize
  end

  def line_two(bottles)
    if bottles == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "Take #{bottles == 1 ? "it" : "one"} down and pass it around, " \
      "#{bottles == 1 ? "no more" : bottles.pred} bottle#{"s" if bottles != 2}" \
      " of beer on the wall.\n"
    end
  end
end
