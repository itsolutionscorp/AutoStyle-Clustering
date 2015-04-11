class BeerSong
  BOTTLES = Hash.new { |hash, key| hash[key] = "#{key} bottles of beer" }
  BOTTLES[1] = "1 bottle of beer"
  BOTTLES[0] = "no more bottles of beer"

  LINE1 = Hash.new do |hash, key|
    hash[key] = "#{BOTTLES[key].capitalize} on the wall, #{BOTTLES[key]}."
  end

  LINE2 = Hash.new do |hash, key|
    hash[key] = "Take #{key == 1 ? 'it' : 'one'} down and pass it around, " \
                "#{BOTTLES[key-1]} on the wall."
  end
  LINE2[0] = "Go to the store and buy some more, #{BOTTLES[99]} on the wall."

  def verse(n)
    "#{LINE1[n]}\n#{LINE2[n]}\n"
  end

  def verses(first, last)
    first.downto(last).map { |n| verse(n) }.join("\n") << "\n"
  end

  def sing
    verses(99, 0)
  end
end
