class Proverb
  
  def initialize *chain
    @options = chain.last.is_a?(Hash) ? chain.pop : {}
    @chain = chain
    @line = "For want of a %s the %s was lost."
    @last_line = "And all for the want of a %s."
  end
  
  def to_s
    @chain.each_cons(2).with_object([]) do |(first_word, second_word), lines|
      lines << (@line % [first_word, second_word])
    end.tap do |lines|
      lines << (@last_line % [@options[:qualifier], @chain.first].compact.join(' '))
    end.join("\n")
  end
  
end
