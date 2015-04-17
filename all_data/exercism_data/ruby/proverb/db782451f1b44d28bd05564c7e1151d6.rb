class Proverb
  def initialize(*args)
    @args = args
  end

  def to_s
    qualifier = create_qualifier
    proverb = []
    many = @args.length - 1
    many.times do |first|
      proverb << "For want of a #{@args[first]} the #{@args[first + 1]} was lost.\n"
      first += 1
    end
    proverb << "And all for the want of a#{qualifier} #{@args[0]}."
    return proverb.join
  end

  def create_qualifier
    if @args[-1].is_a?(Hash)
      " " + @args.pop[:qualifier]
    else
      ""
    end
  end
end