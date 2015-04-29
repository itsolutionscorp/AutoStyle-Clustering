class Proverb

  def initialize(*words, **kwargs)
    @words = words
    @qualifier = kwargs[:qualifier]
  end

  def to_s
    lines = @words[0..-2].zip(@words[1..-1]).map do |a, b|
      "For want of a #{a} the #{b} was lost."
    end
    first = [@qualifier, @words[0]].compact.join " "
    lines.push("And all for the want of a #{first}.").join "\n"
  end

end
