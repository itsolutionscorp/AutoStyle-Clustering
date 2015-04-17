class Beer
  MAX_BEERS = 99
  def sing(first, last = 0)
    first.downto(last).map do |v|
      verse(v)
    end.join("\n") + "\n"
  end

  def verse(num)
    beers = BeerPluralizer.new(num)
    first_line(beers) + second_line(beers)
  end

  private

  def first_line(beers)
    this_many = beers.this_many
    "#{this_many.capitalize} of beer on the wall, #{this_many} of beer.\n"
  end

  def second_line(beers)
    if beers.last_one?
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    elsif beers.all_gone?
      "Go to the store and buy some more, " \
        "#{MAX_BEERS} bottles of beer on the wall.\n"
    else
      "Take one down and pass it around, " \
        "#{beers.one_fewer} of beer on the wall.\n"
    end
  end

  def last_verse?(num)
    num.zero?
  end
end

class BeerPluralizer
  def initialize(count)
    @count = count
  end

  def this_many(this_count = count)
    if this_count.zero?
      "no more bottles"
    elsif this_count == 1
      "1 bottle"
    else
      "#{this_count} #{noun}"
    end
  end

  def one_fewer
    this_many(count - 1)
  end

  def last_one?
    count == 1
  end

  def all_gone?
    count.zero?
  end

  private
  attr_reader :count
  def noun
    last_one? ? 'bottle' : 'bottles'
  end

  def pronoun
    last_one? ? 'it' : 'one'
  end
end