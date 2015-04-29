class BeerSong

  def verse(n)
    SONG[n]
  end

  def verses(upper, lower)
    (upper).downto(lower).map { |n| "#{verse(n)}\n" }.join
  end

  def sing 
    verses(TOTAL_NUMBER_OF_BOTTLES, 0)
  end

  private
  def self.create_song
    (TOTAL_NUMBER_OF_BOTTLES).downto(0).map do |n|
      if n > 0
        verse(n)
      else 
        last_verse(n)
      end
    end.reverse
  end

  def self.last_verse(n)
    "#{pluralize_bottle(n).capitalize}\s#{TEXT[6]}\s#{TEXT[1]},\s#{pluralize_bottle(n)}\s#{TEXT[6]}.\n#{TEXT[5]}\s#{pluralize_bottle(TOTAL_NUMBER_OF_BOTTLES)}\s#{TEXT[6]}\s#{TEXT[1]}.\n"
  end

  def self.verse(n)
    "#{pluralize_bottle(n)}\s#{TEXT[6]}\s#{TEXT[1]},\s#{pluralize_bottle(n)}\s#{TEXT[6]}.\n#{TEXT[2]}\s#{pluralize_one(n)}\s#{TEXT[4]}\s#{pluralize_bottle(n-1)}\s#{TEXT[6]}\s#{TEXT[1]}.\n"
  end

  def self.pluralize_bottle(n)
    case n
    when 0 then "#{TEXT[3]}\s#{TEXT[7]}#{TEXT[10]}"
    when 1 then "#{n}\s#{TEXT[7]}"
    else "#{n}\s#{TEXT[7]}#{TEXT[10]}"
    end
  end

  def self.pluralize_one(n)
    n > 1 ? "#{TEXT[8]}" : "#{TEXT[9]}"
  end

  TOTAL_NUMBER_OF_BOTTLES = 99

  TEXT = {
    1 => "on the wall",
    2 => "Take",
    3 => "no more",
    4 => "down and pass it around,",
    5 => "Go to the store and buy some more,",
    6 => "of beer",
    7 => "bottle",
    8 => "one",
    9 => "it",
    10 => "s"
  }

  SONG = self.create_song

end
