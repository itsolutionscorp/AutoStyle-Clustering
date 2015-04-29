class Proverb
  attr_reader :items, :qualifier

  def initialize(*items, **options)
    @items = items
    @qualifier = options.fetch(:qualifier) { nil }
  end

  def to_s
    [pontifications, conclusion].join "\n"
  end

  private

  def pontifications
    result = []
    @items.each_cons(2) do |cause, consequence|
      result << "For want of a #{cause} the #{consequence} was lost."
    end
    result.join("\n")
  end

  def root_cause
    if qualifier
      [qualifier, first_item].join(" ")
    else
      first_item
    end
  end

  def first_item
    items[0]
  end

  def conclusion
    "And all for the want of a #{root_cause}."
  end

end
