class Beer
  def verse(quantity)
    verse_for(bottles(quantity))
  end

  def sing(*verses)
    start  = verses.fetch(0)
    finish = verses.fetch(1, 0)
    start.downto(finish).each_with_object('') do |line, lyrics|
      lyrics << "#{verse(line)}\n"
    end
  end

  private

  def verse_for(bottles)
    Verse.new(bottles).to_s
  end

  def bottles(quantity)
    Bottles.new(quantity)
  end
end

class Verse
  def initialize(bottles)
    @bottles = bottles
  end

  def to_s
    "#{first_line}\n#{second_line}\n"    
  end

  private

  def first_line
    "#{@bottles.capitalize} of beer on the wall, #{@bottles} of beer."
  end

  def second_line
    if @bottles.gone?
      "Go to the store and buy some more, 99 bottles of beer on the wall."
    else
      "Take #{@bottles.unit} down and pass it around, #{@bottles.remaining} of beer on the wall."
    end
  end
end

class Bottles
  def initialize(quantity)
    @quantity = quantity
  end

  def gone?
    @quantity == 0
  end

  def remaining
    Bottles.new(@quantity - 1)
  end

  def unit
    @quantity > 1 ? 'one' : 'it'
  end

  def capitalize
    to_s.capitalize
  end

  def to_s
    case @quantity
    when 1
      '1 bottle'
    when 0
      'no more bottles'
    else
      "#{@quantity} bottles"
    end
  end
end
