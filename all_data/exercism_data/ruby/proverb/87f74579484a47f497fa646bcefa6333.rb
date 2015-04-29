class Proverb
  def initialize(*nouns, **options)
    @nouns = nouns
    @qualifier = options[:qualifier]
  end

  def to_s
    offset_nouns = @nouns.rotate
    noun_pairs = @nouns.zip(offset_nouns)[0...-1]
    final_noun = [@qualifier, @nouns.first].join(" ").strip
    lines = noun_pairs.map { |first_noun, next_noun|
      "For want of a #{first_noun} the #{next_noun} was lost."
    }.join("\n")
    "#{lines}\nAnd all for the want of a #{final_noun}."
  end
end
