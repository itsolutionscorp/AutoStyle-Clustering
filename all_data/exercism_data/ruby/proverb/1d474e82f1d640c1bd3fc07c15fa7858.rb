class Proverb
  def initialize *args, qualifier: nil
    @chain = args
    @qualifier = qualifier
  end

  def initial_goal
    "#{@qualifier} #{@chain.first}".strip
  end

  def chain_of_steps
    goals = @chain[0..-2]
    consequences = @chain[1..-1]
    goals.zip(consequences)
  end

  def to_s
    result = []
    for goal, consequence in chain_of_steps
      result << "For want of a #{goal} the #{consequence} was lost."
    end
    result << "And all for the want of a #{initial_goal}."
    result.join("\n")
  end
end
