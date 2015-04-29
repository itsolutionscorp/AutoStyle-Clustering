class Proverb
  def initialize *args, qualifier: nil
    @chain = args
    @qualifier = qualifier
  end

  def initial_goal
    "#{@qualifier} #{@chain.first}".strip
  end

  def to_s
    lines = steps_sentences + [ending_sentence]
    lines.join("\n")
  end

  private

  def steps_sentences
    @chain.each_cons(2).map do |goal, cons|
      consequence_sentence(goal, cons)
    end
  end

  def consequence_sentence goal, consequence
    "For want of a #{goal} the #{consequence} was lost."
  end

  def ending_sentence
    "And all for the want of a #{initial_goal}."
  end
end
