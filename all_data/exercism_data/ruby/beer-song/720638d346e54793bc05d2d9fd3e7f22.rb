class Beer
  def verse(n)
    if n > 0
      construct_verse n
    else
      final_verse
    end
  end

  def sing(first, last=0)
    first.downto(last).each_with_object("") { |n, s| s << verse(n) + "\n" }
  end

  private

  def construct_verse(n)
    bottles(n) + " of beer on the wall, " + \
    bottles(n) + " of beer.\n" + \
    "Take " + one_or_it(n) + " down and pass it around, " + \
    bottles(n-1) + " of beer on the wall.\n"
  end

  def final_verse
    "No more bottles of beer on the wall, no more bottles of beer.\n" + \
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def bottles(n)
    if n == 0
      "no more bottles"
    elsif n == 1
      "1 bottle"
    else
      "#{n} bottles"
    end
  end

  def one_or_it(n)
    if n == 1
      "it"
    else
      "one"
    end
  end
end
