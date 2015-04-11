class Proverb
  attr_reader :consequences, :qualifier

  def initialize(*args)
    @consequences = pop_qualifier(args)
  end

  def to_s
    message = ""
    consequences.each_cons(2) do |comparison|
      message += "For want of a #{comparison.first} the #{comparison.last} was lost.\n"
    end
    message += "And all for the want of a #{qualifier}nail."
  end

  private

  def pop_qualifier(args)
    if qualified?(args)
      @qualifier = args.pop[:qualifier] + " "
    else
      @qualifier = ""
    end
    args
  end

  def qualified?(args)
    args.last.class == Hash
  end

end
