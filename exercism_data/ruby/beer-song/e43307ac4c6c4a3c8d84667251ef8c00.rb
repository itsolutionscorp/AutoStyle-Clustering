class BeerSong
  def verse n
    create_verse_generator(n).to_s
  end

  def verses start_no, end_no
    start_no.downto(end_no).map{|n| verse(n) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end
end

def create_verse_generator verse_no
  case verse_no
    when 1 then Verse1Generator.new
    when 0 then Verse0Generator.new
    else VerseGenerator.new verse_no
  end
end

class VerseGenerator
  def initialize verse_no
    @verse_no = verse_no
  end

  def to_s
    "#{print_bottle_count beer_count} on the wall, #{print_bottle_count beer_count}.\n" +
    "#{beginning_of_second_verse}, #{print_bottle_count next_count} on the wall.\n"
  end

private

  def beer_count
    @verse_no
  end

  def next_count
    @verse_no - 1
  end

  def print_bottle_count no_of_bottles
    "#{quantity(no_of_bottles)} bottle#{plural_indicator(no_of_bottles)} of beer"
  end

  def quantity no_of_bottles
    no_of_bottles == 0 ? 'no more' : no_of_bottles.to_s
  end

  def plural_indicator no_of_bottles
    no_of_bottles == 1 ? "" : "s"
  end

  def beginning_of_second_verse
    "Take one down and pass it around"
  end
end

class Verse1Generator < VerseGenerator
  def initialize 
    @verse_no = 1
  end

private

  def beginning_of_second_verse
    "Take it down and pass it around"
  end
end

class Verse0Generator < VerseGenerator
  def initialize 
    @verse_no = 0
  end

  def to_s
    capitalize_first_letter super
  end

private

  def beginning_of_second_verse
    "Go to the store and buy some more"
  end

  def next_count
    99
  end

  def capitalize_first_letter text
    first_char, *other_chars = text.chars.to_a
    [first_char.upcase, *other_chars].join
  end
end
