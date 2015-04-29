class Beer
   
  def sing(start_count, end_count=0)
    @bar = drinking_establishment.new(start_count)
    start_count.downto(end_count).each_with_object("") do |beer_count, song|
      song << verse << "\n"
    end
  end

  def verse(bottle_count=nil)
    @bar ||= drinking_establishment.new(bottle_count)
    @bar.sing
  end
  
  private
    def drinking_establishment
      Bar
    end

end


class Bar

  def initialize(count=99)
    @count = count
  end

  def sing
    "#{bottles_of_beer.capitalize} on the wall, #{bottles_of_beer}.\n"
    .<< bartender_action << "#{bottles_of_beer} on the wall.\n"
  end

  private

    def bartender_action
      deal_with_inventory_crisis || make_a_patron_happy
    end

    def deal_with_inventory_crisis
      return unless out_of_beer?
      @count = 99
      "Go to the store and buy some more, "
    end

    def make_a_patron_happy
      @count -= 1
      "Take #{appropriate_pronoun} down and pass it around, "
    end

    def appropriate_pronoun
      @count == 0 ? 'it' : 'one'
    end

    def bottles_of_beer
      bottle_count_in_words << " " << bottle_pluralized << " of beer"
    end

    def bottle_count_in_words
      out_of_beer? ? "no more" : @count.to_s
    end

    def bottle_pluralized
      only_one_left? ? "bottle" : "bottles"
    end

    def out_of_beer?
      @count == 0
    end

    def only_one_left?
      @count == 1
    end
end
