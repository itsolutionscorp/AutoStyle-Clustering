class Beer

  def song
    99.downto(1) do |i|
      puts verse(i)
      puts ""
    end
    puts last_verse
  end

  def sing(*args)
    return extract_verses(args[0].downto(0)) if args.count == 1
    return extract_verses(args[0].downto(args[-1]))
  end

  def verse(num_botttles)
    return next_to_last_verse if one_more_beer?(num_botttles)
    return last_verse if beer_dry?(num_botttles)
    return <<-eos
#{num_botttles} bottles of beer on the wall, #{num_botttles} bottles of beer.
Take one down and pass it around, #{num_botttles - 1} #{pluralize(num_botttles-1)} of beer on the wall.
eos
  end

  def next_to_last_verse
    return <<-eos
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
eos
  end

  def last_verse
    return <<-eos
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
eos
  end

  private

  def extract_verses(range)
    lines_to_print = range.collect do |i|
        verse(i) + "\n"
      end
    lines_to_print.join(", ").gsub("\n, ", "\n")
  end

  def one_more_beer?(num_botttles)
    num_botttles == 1
  end

  def beer_dry?(num_botttles)
    num_botttles == 0
  end

  def pluralize(bottle_num)
    return "bottles" if bottle_num != 1
    return "bottle"
  end

end
