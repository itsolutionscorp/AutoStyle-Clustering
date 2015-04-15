class Proverb
  attr_reader :nouns, :options
  def initialize(*nouns_and_options)
    @nouns, @options = nouns_and_options_from_hash(nouns_and_options)
  end
  
  def to_s
    verses.join("\n")
  end

  private

  def nouns_and_options_from_hash(options_hash)
    # It's not polite to start popping and mutating someone else's
    # hash, we'll make a copy to be a good citizen so we can do what
    # we want with it
    hash = options_hash.clone
    options = hash.last.is_a?(Hash) ? hash.pop : {}
    return hash, options
  end

  def qualifier
    options[:qualifier]
  end

  def verses
    wanting_verses | [concluding_verse]
  end

  def wanting_verses
    nouns.each_cons(2).collect { |a, b| "For want of a #{a} the #{b} was lost." }
  end

  def concluding_verse
    if qualifier
      "And all for the want of a #{qualifier} #{first_noun}."
    else
      "And all for the want of a #{first_noun}."
    end
  end

  def first_noun
    nouns.first
  end
end
