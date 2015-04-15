class Proverb
  attr_reader :consequences, :qualifier

  def initialize(*args)
    @qualifier = pop_qualifier(args)
    @consequences = args
  end

  def to_s
    verses = consequences.each_cons(2).map do |lack, forfeit|
      "For want of a #{lack} the #{forfeit} was lost.\n"
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

  def qualified?(args)
    args.last.kind_of?(Hash)
  end

end
