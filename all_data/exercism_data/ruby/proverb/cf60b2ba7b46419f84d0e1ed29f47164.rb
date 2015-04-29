class Proverb
  def initialize(*things_lost)
    @things_lost = extract_things(things_lost)
    @qualifier = extract_qualifier(things_lost)
  end

  def to_s
    rhyme_pairs.inject("") do |proverb, pair|
      proverb += "For want of a #{pair.first} the #{pair.last} was lost.\n"
    end + "And all for the want of a #{@qualifier}#{@things_lost.first}."
  end

  private

  def extract_qualifier(things_lost)
    if things_lost.last.kind_of? Hash
      things_lost.last[:qualifier] + " "
    else
      ""
    end
  end

  def extract_things(things_lost)
    if things_lost.last.kind_of? Hash
      things_lost[0...-1]
    else
      things_lost
    end
  end

  def rhyme_pairs
    @things_lost.each_cons(2)
  end
end
