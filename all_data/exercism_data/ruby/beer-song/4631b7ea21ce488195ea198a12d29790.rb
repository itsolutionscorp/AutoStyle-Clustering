class BeerSong
  def verse(num)
    "#{_beer_on_wall_sentence_start num} bottle#{_plural_s num} of beer on the wall, #{_beer_on_wall num} bottle#{_plural_s num} of beer.\n" \
      "#{_do_next num} #{_next_num num} bottle#{_next_plural_s num} of beer on the wall.\n"
  end

  def _do_next num
    num == 0 ? 'Go to the store and buy some more,' : "Take #{_down_bottle num} down and pass it around,"
  end

  def _down_bottle(num)
    _singular?(num) ? 'one' : 'it'
  end

  def _next_plural_s(num)
    num != 2 ? 's' : ''
  end

  def _plural_s(num)
    _singular?(num) ? 's' : ''
  end

  def _next_num(num)
  	return 99 if num == 0
    _singular?(num) ? num - 1 : 'no more'
  end

  def _beer_on_wall(num)
    num == 0 ? _beer_on_wall_sentence_start(num).downcase : num
  end

  def _beer_on_wall_sentence_start(num)
    num == 0 ? 'No more' : num
  end

  def _singular?(num)
    num != 1
  end

  def _zeroith?(num)
    num == 0
  end

  def verses(large_verse, small_verse)
    large_verse.downto(small_verse).map { |num| verse num }.join("\n")[0..-1] + "\n"
  end

  def sing
    verses(99, 0)
  end
end
