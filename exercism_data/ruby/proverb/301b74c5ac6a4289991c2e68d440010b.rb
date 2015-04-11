class Proverb
  attr_reader :args
  attr_reader :qualifier

  def initialize(*args)
    self.qualifier = args
    @args = *args
  end

  def qualifier=(args)
    @qualifier = args.last.is_a?(Hash) ? args.pop.fetch(:qualifier, nil) : ''
  end

  def to_s
    res = print_proverb(args)
    res += "And all for the want of a #{qualified_lack(args[0])}."
  end

  def qualified_lack(lack)
    qualifier.empty? ? "#{lack}" : "#{qualifier} #{lack}"
  end

  def print_proverb(args)
    res = ""
    args.each_cons(2) do |missing, item|
      res += "For want of a #{missing} the #{item} was lost.\n"
    end
    res
  end
end
