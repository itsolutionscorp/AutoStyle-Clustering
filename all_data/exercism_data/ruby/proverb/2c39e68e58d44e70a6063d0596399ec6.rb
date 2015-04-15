class Proverb
  attr_reader :causes, :qualifier

  def initialize first_cause, first_effect, *more_effects, qualifier: nil
    @causes = first_cause, first_effect, *more_effects
    @qualifier = qualifier
  end

  def to_s
    lines.join("\n")
  end

  private

  def lines
    causes
    .each_cons(2)
    .map do |cause,effect|
      repeating_line cause, effect
    end << last_line
  end

  def repeating_line cause, effect
    "For want of a #{cause} the #{effect} was lost."
  end

  def last_line
    "And all for the want of a #{qualified causes.first}."
  end

  def qualified noun
    [ qualifier, noun ]
    .compact
    .join " "
  end
end
