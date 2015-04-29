class BeerSong
  def initialize
    @song = Hash.new do |song, verse|
      song[verse] = if verse == 0 then
                      "#{bottle(verse).capitalize} of beer on the wall, #{bottle verse} of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
                    else
                      "#{bottle verse} of beer on the wall, #{bottle verse} of beer.\nTake #{descriptor verse} down and pass it around, #{bottle verse-1} of beer on the wall.\n"
                    end
    end
  end

  def verse n
    @song[n]
  end

  def verses from, to
    @song[to..from].reverse.map{|i| i << "\n"}.reduce(:<<)
  end

  def sing
    verses 99, 0
  end

  private

  def bottle n
    case n
    when 0
      "no more bottles"
    when 1
      "1 bottle"
    else
      "#{n} bottles"
    end
  end

  def descriptor n
    n == 1 ? "it" : "one"
  end
end

Hash.class_eval do
  alias_method :index_fetch, :[]
  def [] n
    if n.class == Range then
      n.map{|i| index_fetch i}
    else
      index_fetch n
    end
  end
end
