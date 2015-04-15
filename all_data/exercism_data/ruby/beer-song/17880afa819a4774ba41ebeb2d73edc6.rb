class Beer
  def sing(start_verse, end_verse = 0)
    verses = start_verse.downto(end_verse).inject('') do |verses, verse_number|
      verses + verse(verse_number) + "\n"
    end
  end

  def verse(verse_number)
    Verse.new(verse_number, 'beer').to_s
  end
end

class Verse
  attr_reader :quantity, :beverage

  def initialize(verse_number, beverage)
    @quantity = verse_number
    @beverage = beverage
  end

  def to_s
    if zero_bottles?
      "No more bottles of #{beverage} on the wall, no more bottles of #{beverage}.\nGo to the store and buy some more, 99 bottles of #{beverage} on the wall.\n"
    elsif one_bottle?
      "#{quantity} bottle of #{beverage} on the wall, #{quantity} bottle of #{beverage}.\nTake it down and pass it around, no more bottles of #{beverage} on the wall.\n"
    elsif two_bottles?
      "#{quantity} bottles of #{beverage} on the wall, #{quantity} bottles of #{beverage}.\nTake one down and pass it around, #{quantity - 1} bottle of #{beverage} on the wall.\n"
    else
      "#{quantity} bottles of #{beverage} on the wall, #{quantity} bottles of #{beverage}.\nTake one down and pass it around, #{quantity - 1} bottles of #{beverage} on the wall.\n"
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
