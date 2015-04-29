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
    qualifier = (@options[:qualifier] && @options[:qualifier] + ' nail') || @words[0]
    (0..@words.length).each_cons(2).map do |indexes|
      if indexes[0] == @words.length-1
        "And all for the want of a #{qualifier}."
      else
        "For want of a #{@words[indexes[0]]} the #{@words[indexes[1]]} was lost.\n"
      end
    end.join()
  end
end
