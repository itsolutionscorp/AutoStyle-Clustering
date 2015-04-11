class Beer
  def verse(n)
    n > 0 ? early_verse(n) : final_verse
  end

  def sing(first, last=0)
    first.downto(last).each_with_object("") { |n, s| s << verse(n) + "\n" }
  end

  private

  def early_verse(n)
    [bottles(n) + " of beer on the wall, ",
    bottles(n) + " of beer.\n",
    "Take " + pronoun(n) + " down and pass it around, ",
    bottles(n-1) + " of beer on the wall.\n"].join
  end

  def final_verse
    ["No more bottles of beer on the wall, no more bottles of beer.\n",
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"].join
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

  def pronoun(n)
    n == 1 ? "it" : "one"
  end
end
