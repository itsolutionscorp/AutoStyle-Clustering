class Proverb
  attr_reader :args
  attr_reader :qualifier

  def initialize(*args)
    self.qualifier = args

    if qualifier.empty?
      @args = *args
    else
      @args = args[0..-2]
    end
  end

  def qualifier=(args)
    @qualifier = args[-1].is_a?(Hash) ? args[-1].fetch(:qualifier, '') : ''
  end

  def to_s
    res = print_proverb("", args[0], args[1..-1])
    res += "And all for the want of a #{qualified_lack(args[0])}."
  end

  def qualified_lack(lack)
    qualifier.empty? ? "#{lack}" : "#{qualifier} #{lack}"
  end

  def print_proverb(res, lack, rest)
    second = rest[0]

    unless second
      return res
    end

    res += "For want of a #{lack} the #{second} was lost.\n"

    res = print_proverb(res, second, rest[1..-1])
  end
end
