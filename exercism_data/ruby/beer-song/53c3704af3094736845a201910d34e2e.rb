class Beer
  def verse(n)
    if n > 0
      pluralize(n, "bottle") + " of beer on the wall, " + \
      pluralize(n, "bottle") + " of beer.\n" + \
      "Take " + one_or_it(n) + " down and pass it around, " + \
      pluralize(n-1, "bottle") + " of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\n" + \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def sing(first, last=0)
    first.downto(last).each_with_object("") { |n, s| s << verse(n) + "\n" }
  end

  private

  def pluralize(n, singular)
    if n == 0
      "no more #{singular}s"
    elsif n == 1
      "1 #{singular}"
    else
      "#{n} #{singular}s"
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
