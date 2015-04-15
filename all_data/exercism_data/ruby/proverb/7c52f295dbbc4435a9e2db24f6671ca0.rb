class Proverb
  def initialize(*words)
    @words = *words
  end

  def to_s
    if @words.last.class == Hash
      @qualifier = @words.last[:qualifier]
      proverb_string = ""
      @words.each_with_index do |_, index|
        proverb_string << "For want of a #{@words[index]}"
        proverb_string << " the #{@words[index + 1]} was lost.\n"
        break if index == @words.length - 3
      end
      proverb_string << "And all for the want of a #{@qualifier} #{@words[0]}."
    else
      proverb_string = ""
      @words.each_with_index do |_, index|
        proverb_string << "For want of a #{@words[index]}"
        proverb_string << " the #{@words[index + 1]} was lost.\n"
        break if index == @words.length - 2
      end
      proverb_string << "And all for the want of a #{@words[0]}."
    end

  end
end
