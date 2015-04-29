class Proverb
  def initialize(*things_with_options)
    @options = extract_options!(things_with_options)
    @things = things_with_options
  end

  def to_s
    [exchanges, summary].flatten.join("\n")
  end

  private

  def exchanges
    @things.each_cons(2).map { |(target, given)| exchange(target, given) }
  end

  def exchange(target, given)
    "For want of a #{target} the #{given} was lost."
  end

  def summary
    "And all for the want of a #{qualified_target}."
  end

  def qualified_target
    [qualifier, target].compact.join(' ')
  end

  def target
    @things.first
  end

  def qualifier
    @options[:qualifier]
  end

  def extract_options!(arg)
    if arg.last.is_a?(Hash)
      arg.pop
    else
      {}
    end
  end
end
