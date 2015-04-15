class Proverb
  def initialize(*args)
    @words = args
    @last = nil
    strip
  end

  def to_s
    last = get_last
    @words.each_with_index.with_object("") do |(word, i), results|
      if i == @words.length-1
        results << "And all for the want of a #{last}."
      else
        next_word = @words[i+1]
        results << "For want of a #{word} the #{next_word} was lost.\n"
      end
    end
  end

  private

  def strip
    if @words.last.is_a?(Hash)
      @last = @words.pop[:qualifier]
    end
  end

  def get_last
    @last ? "#{@last} #{@words.first}" : @words.first
  end

end
