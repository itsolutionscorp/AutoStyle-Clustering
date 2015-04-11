class Proverb
  attr_reader :words, :qualifier

  def initialize(*words)
    @words, @qualifier = parse_input(words)
  end

  def to_s
    proverb = ""
    @words.each_with_index do |word, i|
      if add_consequence?(i)
        proverb << for_want_of_a(word, @words[i+1])
      end
    end
    proverb << end_of_proverb
  end

  private

  def parse_input(words)
    if words.last.is_a? Hash
      [words[0..-2], words.last[:qualifier]]
    else
      [words, nil]
    end
  end

  def add_consequence?(index)
    index + 1 < @words.size
  end

  def for_want_of_a(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def end_of_proverb
    if @qualifier
      "And all for the want of a #{@qualifier} #{@words.first}."
    else
      "And all for the want of a #{@words.first}."
    end
  end
end
