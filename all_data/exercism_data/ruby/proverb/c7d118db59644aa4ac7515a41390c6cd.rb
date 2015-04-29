class Proverb
  def initialize(*args)
    @args = args
  end

  def to_s
    qualifier = create_qualifier
    proverb = build_proverb(qualifier)
    return proverb
  end

  def create_qualifier
    @args.last.is_a?(Hash) ? " " + @args.pop[:qualifier] : ""
  end

  def build_proverb(qualifier)
    proverb = []
    (@args.length - 1).times do |first|
      proverb << "For want of a #{@args[first]} the #{@args[first + 1]} was lost.\n"
      first += 1
    end
    proverb << "And all for the want of a#{qualifier} #{@args[0]}."
    return proverb.join
  end
end
