class Proverb
  def initialize(*words, specifier)
    @words = words
    default = {specifier: @words.first}
    options = default.merge(options)
  end

  def to_s
    proverb = ""
    word_pairs.each do |pair|
      proverb += "For want of a " + pair.first + " the " + pair.last + " was lost.\n"
    end
    return proverb + "And all for the want of a #{options[:specifier]}."
  end

  private

  def word_pairs
    @words.zip(@words.drop(1))[0...-1]
  end
end
