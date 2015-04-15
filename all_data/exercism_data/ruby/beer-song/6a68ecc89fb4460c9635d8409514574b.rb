class Beer

  def verse(num_bottles)
    BeerVerse.new(num_bottles).to_s
  end

  def sing(starting_bottles, ending_bottles=0)
    if starting_bottles == ending_bottles
      verse(ending_bottles) + "\n"
    else
      verse(starting_bottles) + "\n" + sing(starting_bottles-1, ending_bottles)
    end
  end

end

class BeerVerse

  def initialize(num_bottles)
    @num_bottles = num_bottles
  end

  attr_reader :num_bottles

  def to_s
    "#{first_bottles_clause} of beer on the wall, #{second_bottles_clause} of beer.\n" \
    "#{action_clause}, #{bottles_remaining_clause} of beer on the wall.\n"
  end

  def first_bottles_clause
    bottles_clause.capitalize
  end

  def second_bottles_clause
    bottles_clause
  end

  def bottles_remaining_clause
    bottles_clause(num_bottles-1)
  end

  def bottles_clause(bottles=num_bottles)
    bottles = 99 if bottles < 0
    case bottles
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{bottles} bottles"
    end
  end

  def action_clause
    case num_bottles
    when 0 then "Go to the store and buy some more"
    when 1 then "Take it down and pass it around"
    else "Take one down and pass it around"
    end
  end

end
