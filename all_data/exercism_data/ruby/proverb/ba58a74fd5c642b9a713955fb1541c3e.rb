class Proverb
  attr_reader :consequences, :qualifier

  def initialize(*args)
    @qualifier = pop_qualifier(args)
    @consequences = just_the_verses(args)
  end

  def to_s
    verses = consequences.each_cons(2).map do |comparison|
      "For want of a #{comparison.first} the #{comparison.last} was lost.\n"
    end
    verses.join + "And all for the want of a #{qualifier}nail."
  end

  private

  def pop_qualifier(args)
    if qualified?(args)
     args.pop[:qualifier] + " "
    else
      ""
    end
  end

  def just_the_verses(args)
    if qualified?(args)
      args[0..-1]
    else
      args
    end
  end

  def qualified?(args)
    args.last.kind_of?(Hash)
  end

end
