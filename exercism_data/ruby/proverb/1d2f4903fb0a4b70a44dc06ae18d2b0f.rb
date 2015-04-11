class Proverb
  def initialize(*chain, qualifier: '')
    @chain = chain
    @qualifier = qualifier
  end

  def to_s
    (consequences << conclusion).join("\n")
  end

  private

  def consequences
    (1...@chain.size).map do |i|
      "For want of a #{@chain[i-1]} the #{@chain[i]} was lost."
    end
  end

  def conclusion
    "And all for the want of a #{@qualifier} #{@chain.first}.".squeeze(' ')
  end
end
