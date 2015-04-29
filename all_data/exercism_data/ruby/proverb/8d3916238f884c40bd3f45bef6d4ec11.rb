class Proverb
  def initialize(*words)
    @qualifier = words.extract_options[:qualifier]
    @words = words
  end

  def to_s
    (consequences << final_consequence).join("\n")
  end

  private

  def consequences
    @words.each_cons(2).map { |want, lost| consequence(want, lost) }
  end

  def consequence(want, lost)
    "For want of a #{want} the #{lost} was lost."
  end

  def final_consequence
    "And all for the want of a #{qualifier.to_s + @words.first}."
  end

  def qualifier
    "#{@qualifier} " if @qualifier
  end
end

class Array
  def extract_options
    last.is_a?(Hash) ? pop : {}
  end
end
