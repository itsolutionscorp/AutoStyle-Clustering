class Proverb

  attr_reader :words

  def initialize(*words)
    @qualifier = ''
    create_proverb_list(words)
    create_final_line(words[-1])
  end

  def create_proverb_list(words)
    words[-1].class == Hash ? @words = words[0..-2] : @words = words
  end

  def create_final_line(option)
    @qualifier = option[:qualifier] + ' ' if option.class == Hash
  end

  def to_s
    string = phrase(words)
  end

  def phrase(words)
    words.length > 1 ? for_phrase(words) + phrase(words[1..-1]) : final_line
  end

  def for_phrase(words)
    "For want of a #{words[0]} the #{words[1]} was lost.\n"
  end

  def final_line
    "And all for the want of a #{@qualifier + words[0]}."
  end

end
