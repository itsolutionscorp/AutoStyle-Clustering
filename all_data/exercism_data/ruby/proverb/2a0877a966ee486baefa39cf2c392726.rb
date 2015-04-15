class Proverb
  def initialize (*words)
    @words = words
    if @words.last.is_a?(Hash)
      @qualifier = words.pop
    end
  end

  def to_s
    proverb = ""
    for i in 0...(@words.length - 1)
      proverb << "For want of a #{@words[i]} the #{@words[i+1]} was lost.\n"
    end
    if @qualifier.nil?
      proverb << "And all for the want of a #{@words[0]}."
    else
      proverb << "And all for the want of a #{@qualifier[:qualifier]} #{@words[0]}."
    end
  end
end
