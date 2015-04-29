class Proverb
  attr_reader :words, :qualifier

  def initialize(*words, qualifier: nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    if has_qualifier?
      add_qualifier_to_proverb(generate_proverb).join
    else
      generate_proverb.join
    end
  end

  def has_qualifier?
    qualifier != nil
  end

  def add_qualifier_to_proverb(proverb_array)
    proverb_array[proverb_array.count-1] = "And all for the want of a #{qualifier} #{words.first}."
    proverb_array
  end

  def generate_proverb()
    phrase = []
    words.each_with_index { |item, index|
      if item == words.last && qualifier == nil
        phrase << "And all for the want of a #{words[0]}."
      else
        phrase << "For want of a #{item} the #{words[index+1]} was lost.\n"
      end
    }
    phrase
  end
end
