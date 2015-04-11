class Proverb
  def initialize *nouns, qualifier: nil
    @nouns = nouns
    @qualified_noun = [qualifier, nouns[0]].compact.join ' '
  end

  def to_s
    lines = @nouns[0..-2].zip(@nouns[1..-1]).map do |first_noun, second_noun|
      "For want of a #{first_noun} the #{second_noun} was lost."
    end
    (lines << "And all for the want of a #{@qualified_noun}.").join "\n"
  end
end
