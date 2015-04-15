class Proverb
  def initialize(*args)

    @words = *args
    if @words.last.kind_of?(Hash)
      @qual = @words.pop[:qualifier] + " "
    end
    @first = @words.first
  end

  def to_s

    word = @words.select {|word| word}

    lines = ""
    (word.count - 1).times do

      first = word[0]
      second = word[1]
      line = "For want of a #{first} the #{second} was lost.\n"
      lines << line
      word.shift


    end
    ans = "And all for the want of a #{@qual}#{@first}."
    return lines << ans
  end
end
