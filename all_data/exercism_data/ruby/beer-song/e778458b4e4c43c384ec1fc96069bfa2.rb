class Beer
  attr_accessor :bottle_quantity
  attr_reader :start_verse, :end_verse

  def initialize
    @bottle_quantity = nil
  end

  def sing(start_verse, end_verse = 0)
    @start_verse = start_verse
    @end_verse = end_verse

    requested_verses
  end

  def verse(verse_number)
    @bottle_quantity = verse_number

    if one_bottle?
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif two_bottles?
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif zero_bottles?
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{bottle_quantity} bottles of beer on the wall, #{bottle_quantity} bottles of beer.\nTake one down and pass it around, #{bottle_quantity - 1} bottles of beer on the wall.\n"
    end
  end

  private

  def requested_verses
    requested_verse_numbers = Array(end_verse..start_verse).reverse
    requested_verses = ""

    requested_verse_numbers.each do |requested_verse_number|
      requested_verses << verse(requested_verse_number) + "\n"
    end

    requested_verses
  end

  def one_bottle?
    bottle_quantity == 1
  end

  def two_bottles?
    bottle_quantity == 2
  end

  def zero_bottles?
    bottle_quantity == 0
  end

end
