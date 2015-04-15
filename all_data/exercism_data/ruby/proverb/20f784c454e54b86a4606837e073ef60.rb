class Proverb
  def initialize(*wants)
    @wants = wants
  end

  def to_s
    all_consequences.join("\n")
  end

  private

  def all_consequences
    chain_of_consequences << final_consequence
  end

  def final_consequence
    "And all for the want of a #{final_consequence_want}."
  end

  def chain_of_consequences
    (1..number_of_consequences).map do |index|
      "For want of a #{@wants[index - 1]} the #{@wants[index]} was lost."
    end
  end

  def qualifier
    return unless has_qualifier?
    @wants.last[:qualifier]
  end

  def final_consequence_want
    [qualifier, first_want].compact.join(' ')
  end

  def number_of_consequences
    @wants.size - chain_offset
  end

  def chain_offset
    has_qualifier? ? 2 : 1
  end

  def has_qualifier?
    @wants.last.class == Hash
  end

  def first_want
    @wants.first
  end
end
