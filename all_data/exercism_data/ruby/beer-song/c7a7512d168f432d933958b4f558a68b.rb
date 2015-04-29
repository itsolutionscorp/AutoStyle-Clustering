class BeerSong
  def verse(num)
    singular = num != 1

    beer_on_wall_sentence_start = num == 0 ? 'No more' : num
    beer_on_wall = num == 0 ? beer_on_wall_sentence_start.downcase : num
    next_num = singular ? num - 1 : 'no more'
    next_num = next_num == -1 ? 99 : next_num
    plural_s = singular ? 's' : ''
    next_plural_s = num != 2 ? 's' : ''
    down_bottle = singular ? 'one' : 'it'
    do_next = num == 0 ? 'Go to the store and buy some more,' : "Take #{down_bottle} down and pass it around,"

    "#{beer_on_wall_sentence_start} bottle#{plural_s} of beer on the wall, #{beer_on_wall} bottle#{plural_s} of beer.\n" \
      "#{do_next} #{next_num} bottle#{next_plural_s} of beer on the wall.\n"
  end

  def verses(large_verse, small_verse)
    large_verse.downto(small_verse).map { |num| verse num }.join("\n")[0..-1] + "\n"
  end

  def sing
  	verses(99, 0)
  end
end
