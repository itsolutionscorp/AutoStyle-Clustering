class Proverb
  attr_reader :words, :qualifier

  def initialize(*words, qualifier: nil)
    @words = words
    @qualifier = qualifier
  end

  def to_s
    phrase = []
    words.each_with_index { |item, index|
      if item == words.last && qualifier == nil
        phrase << "And all for the want of a #{words[0]}."
      else
        phrase << "For want of a #{item} the #{words[index+1]} was lost.\n"
      end
    }
    if qualifier != nil
      phrase[phrase.count-1] = "And all for the want of a #{qualifier} #{words.first}."
    end
    phrase.join
  end
end
