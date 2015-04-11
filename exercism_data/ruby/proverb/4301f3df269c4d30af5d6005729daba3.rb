class Proverb

  attr_reader :desired, :qualifier

  def initialize(*desired_verses)
    if desired_verses.last.class == Hash
      @desired = desired_verses[0..-3]
      @qualifier = desired_verses.last
    else
      @desired = desired_verses[0..-2]
      @qualifier = nil
    end
  end

  def to_s
    convert_desired_to_proverb << last_verse
  end

  private

  def convert_desired_to_proverb
    desired.inject("") do |proverb, item|
      proverb << verse(item)
    end
  end

  def verse(item)
    "For want of a #{item} the #{item_relations[item]} was lost.\n"
  end

  def last_verse
    if qualifier
      "And all for the want of a #{qualifier[:qualifier]} nail."
    else
      "And all for the want of a nail."
    end
  end

  def item_relations
    {
      "nail"    => "shoe",
      "shoe"    => "horse",
      "horse"   => "rider",
      "rider"   => "message",
      "message" => "battle",
      "battle"  => "kingdom"
    }
  end

end
