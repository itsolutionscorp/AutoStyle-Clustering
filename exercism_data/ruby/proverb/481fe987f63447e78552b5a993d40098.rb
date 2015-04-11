class Proverb
  def initialize(*args, qualifier: nil)
    @qualifier = qualifier
    @consequences = args
  end

  def to_s
    @consequences.each_cons(2).map do |lack, forfeit|
      "For want of a #{lack} the #{forfeit} was lost."
    end
    .push(last_line)
    .join("\n")
  end

  private

  def last_line
    "And all for the want of a #{@qualifier + " " if @qualifier}#{@consequences.first}."
  end

end
