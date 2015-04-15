class BeerSong
  def initialize
    @verse = Hash.new do |_, x|
      "#{x} bottles of beer on the wall, #{x} bottles of beer.\n" \
      "Take one down and pass it around, #{x - 1} bottles of beer on the wall.\n"
    end

    @verse[0] =
      "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    @verse[1] = @verse[1].gsub(/1 bottles/, '1 bottle').gsub(/0/, 'no more').sub(/one/, 'it')
    @verse[2] = @verse[2].gsub(/1 bottles/, '1 bottle')
  end

  def verse(n)
    @verse[n]
  end

  def verses(start, stop)
    start.downto(stop).map { |x| verse(x) }.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end
end
