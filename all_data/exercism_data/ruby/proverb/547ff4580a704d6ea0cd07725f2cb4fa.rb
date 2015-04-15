class Proverb
  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualifier = qualifier
  end

  def to_s
    body + footer
  end

private
  attr_reader :chain, :qualifier

  def body
    chain.each_cons(2).map { |word_a, word_b|
      "For want of a #{word_a} the #{word_b} was lost.\n"
    }.join
  end

  def footer
    "And all for the want of a #{qualifier} #{chain.first}.".squeeze(" ")
  end
end
