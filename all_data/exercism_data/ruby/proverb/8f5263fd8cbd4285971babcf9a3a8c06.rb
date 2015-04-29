class Proverb
  def initialize(*conditions, qualifier: nil)
    @conditions = conditions
    @qualifier = qualifier
  end

  def to_s
    body.push(concluding_line).join("\n")
  end

  private

  def body
    chain_of_events.map { |cause, consequence| narrate(cause, consequence) }
  end

  def narrate(cause, consequence)
    "For want of a #{cause} the #{consequence} was lost."
  end

  def concluding_line
    "And all for the want of a #{first_cause}."
  end

  def chain_of_events
    @conditions.each_cons(2)
  end

  def first_cause
    [@qualifier, @conditions.first].compact.join(' ')
  end
end
