class BeerSong
  def verse(n)
    "".tap do |str|
      str << first_line(n)
      str << second_line(n)
    end
  end

  def verses(start, finish)
    start.downto(finish).map(&method(:verse)).join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end

  private

  def n_bottles(n)
    {
      0 => %{No more bottles},
      1 => %{1 bottle}
    }.fetch(n){ %{#{n} bottles} } << " of beer"
  end

  def first_line(n)
    %{#{n_string = n_bottles(n)} on the wall, #{n_string.downcase}.\n}
  end

  def second_line(n)
    return %{Go to the store and buy some more, #{n_bottles(99)} on the wall.\n} if n.zero?
    %{Take #{n == 1 ? "it" : "one"} down and pass it around, #{n_bottles(n - 1).downcase} on the wall.\n}
  end

end
