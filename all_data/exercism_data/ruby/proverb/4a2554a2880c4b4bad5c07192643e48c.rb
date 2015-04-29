class Proverb
  def initialize(*words)
    @words, @qualifier = handle_inputs(words)
  end

  def to_s
    result = ""
    rhyme = @words.first
    @words.each_cons(2) do |word_1, word_2|
      result << "For want of a #{word_1} the #{word_2} was lost.\n"
    end
    result << "And all for the want of a #{@qualifier + rhyme}."
  end

  private

  def handle_inputs(array)
    if array.last.kind_of?(Hash)
      return array.slice(0, array.size-1), array.last[:qualifier] + " "
    end

    return array, ""
  end

end
