class Proverb
  def initialize *words
    @words = words
    @qualifier = ''

    if @words.last.kind_of? Hash
      @qualifier = @words.last[:qualifier] + ' '
      @words = @words[0..-2]
    end
  end

  def to_s
    (@words.length - 1).downto(1).to_a.reverse.map do |index|
      "For want of a #{@words[index-1]} the #{@words[index]} was lost.\n"
    end.inject(:+) +
      "And all for the want of a #{@qualifier}#{@words.first}."
  end
end
