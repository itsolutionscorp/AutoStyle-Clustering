class Proverb
  def initialize(*wants)
    @options = wants.last.is_a?(Hash) ? wants.pop : {}
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
    @wants.each_cons(2).map do |want, lost|
      "For want of a #{want} the #{lost} was lost."
    end
  end

  def qualifier
    @options[:qualifier]
  end

  def final_consequence_want
    [qualifier, first_want].compact.join(' ')
  end

  def first_want
    @wants.first
  end
end
