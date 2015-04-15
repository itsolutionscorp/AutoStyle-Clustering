class Proverb
  def initialize *words, options
    unless options.is_a? Hash
      words << options
      options = {}
    end
    @words = words
    @options = options
  end

  def to_s
    lastIndex = @words.length - 1
    if @options[:qualifier]
      qualifier = @options[:qualifier] + ' nail'
    else
      qualifier = @words[0]
    end
    @words.each_with_index.map do |word, index|
      if index == lastIndex
        "And all for the want of a #{qualifier}."
      else
        "For want of a #{word} the #{@words[index+1]} was lost.\n"
      end
    end.join()
  end
end
