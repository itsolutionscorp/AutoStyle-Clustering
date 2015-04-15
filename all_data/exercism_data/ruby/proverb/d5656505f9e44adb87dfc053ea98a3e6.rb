class Proverb
  def initialize(*words)
    @words = words
    @qualifier = ""

    if @words.last.kind_of? Hash
      @qualifier = @words.last[:qualifier] + " "
      @words = @words[0...-1]
    end

  end

  def to_s
    word_pairs.inject("") do |proverb, pair|
      proverb += "For want of a #{pair.first} the #{pair.last} was lost.\n"
    end + "And all for the want of a #{@qualifier}#{@words.first}."
  end

  private

  def word_pairs
    @words.zip(@words.drop(1))[0...-1]
  end
end
