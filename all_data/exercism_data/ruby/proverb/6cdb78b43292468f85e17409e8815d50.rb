class Proverb
  def initialize(*words)
    if words.last.is_a?(Hash)
      qualifier = words.pop[:qualifier]
      @qualifier = ' ' + qualifier
      @words = words
    else
      @words = words
    end
  end

  def to_s
    output = String.new
    @words.each_with_index do |word, index|
      break if @words[index+1] == nil
        output << "For want of a #{word} the #{@words[index+1]} was lost.\n"
    end
    output << "And all for the want of a#{@qualifier} #{@words.first}."
  end
end
