class Proverb
  def initialize(*wants, qualifier: nil)
    @wants, @qualifier = wants, qualifier
  end

  def to_s
    (consequences << final_consequence).join("\n")
  end

  private

  def consequences
    @wants.each_cons(2).map { |want, lost| consequence(want, lost) }
  end

  def consequence(want, lost)
    "For want of a #{want} the #{lost} was lost."
  end

  def final_consequence
    "And all for the want of a #{final_want}."
  end

  def final_want
    qualifier + @wants.first
  end

  def qualifier
    @qualifier ? "#{@qualifier} " : ''
  end
end
