class BeerSong

  def verse(n_bottles)
    BeerVerse.new(n_bottles).to_s
  end

  def verses(starting_bottles=default_starting_bottles, ending_bottles=0)
    if starting_bottles == ending_bottles
      verse(ending_bottles) + "\n"
    else
      verse(starting_bottles) + "\n" + verses(starting_bottles-1, ending_bottles)
    end
  end

  def sing
    verses
  end

  private

  def default_starting_bottles
    99
  end

end

class BeerVerse

  def initialize(n_bottles)
    @n_bottles = n_bottles
  end

  attr_reader :n_bottles

  def to_s
    "#{bottles_fragment.capitalize} of beer on the wall, #{bottles_fragment} of beer.\n" \
    "#{action_fragment}, #{bottles_remaining_fragment} of beer on the wall.\n"
  end

  def bottles_remaining_fragment
    bottles_fragment(n_bottles-1)
  end

  def bottles_fragment(bottles=n_bottles)
    bottles = 99 if bottles < 0
    case bottles
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{bottles} bottles"
    end
  end

  def action_fragment
    case n_bottles
    when 0 then "Go to the store and buy some more"
    when 1 then "Take it down and pass it around"
    else "Take one down and pass it around"
    end
  end

end
