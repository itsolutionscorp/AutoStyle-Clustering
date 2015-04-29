class Proverb
  def initialize(*words)
    @words, @qualifier = handle_inputs(words)
  end

  def to_s
    result = ""
    words = @words.clone
    rhyme = @words.first
    (0..@words.size - 2).step do
      result << "For want of a #{words[0]} the #{words[1]} was lost.\n"
      words.shift
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
