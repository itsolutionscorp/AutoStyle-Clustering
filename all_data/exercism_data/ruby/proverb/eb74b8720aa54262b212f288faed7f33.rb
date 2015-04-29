class Proverb

  def initialize(*words, qualifier: qualifier)
    @words = words
    @qualifier = qualifier + ' ' if !qualifier.nil?
  end

  def to_s
    lines = (0..@words.count-2).map do |n|
      "For want of a #{@words[n]} the #{@words[n+1]} was lost."
    end
    final = "And all for the want of a #{@qualifier}#{@words[0]}."
    lines.join("\n") + "\n" + final
  end

end
