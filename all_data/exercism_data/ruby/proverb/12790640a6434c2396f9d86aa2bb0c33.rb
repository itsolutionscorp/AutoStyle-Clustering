class Proverb
  def initialize(*words)
    @words = *words
    if @words.last.class == Hash
      @qualifier = @words.last[:qualifier]
      @words.pop
    end
  end

  def to_s
    start = ''
    count = 0
    @words.each do |w|
      unless w == @words.last
        count += 1
        start += "For want of a #{w} the #{@words[count]} was lost.\n"
      end
    end

    if @qualifier
      finish = "And all for the want of a #{@qualifier} #{@words.first}."
    else
      finish = "And all for the want of a #{@words.first}."
    end

    return start + finish
  end
end
