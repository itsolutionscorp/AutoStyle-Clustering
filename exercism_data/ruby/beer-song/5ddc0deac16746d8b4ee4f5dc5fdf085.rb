class Beer

  def sing(start_verse, end_verse = 0)
    verses = start_verse.downto(end_verse).inject('') do |verses, verse_number|
      verses + Verse.new(verse_number).to_s + "\n"
    end
  end

  def verse(verse_number)
    Verse.new(verse_number).to_s
  end

end

class Verse
  attr_reader :quantity

  def initialize(verse_number)
    @quantity = verse_number
  end

  def to_s

    if zero_bottles?
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif one_bottle?
      "#{quantity} bottle of beer on the wall, #{quantity} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif two_bottles?
      "#{quantity} bottles of beer on the wall, #{quantity} bottles of beer.\nTake one down and pass it around, #{quantity - 1} bottle of beer on the wall.\n"
    else
      "#{quantity} bottles of beer on the wall, #{quantity} bottles of beer.\nTake one down and pass it around, #{quantity - 1} bottles of beer on the wall.\n"
    end
  end

  private

  def zero_bottles?
    quantity == 0
  end

  def one_bottle?
    quantity == 1
  end

  def two_bottles?
    quantity == 2
  end

end
