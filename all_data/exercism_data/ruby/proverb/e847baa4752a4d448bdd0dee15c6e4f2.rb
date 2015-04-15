class Proverb
  def initialize(*args, qualifier: nil)
    @qualifier = qualifier
    @consequences = args
  end

  def to_s
    @consequences.each_cons(2).map do |loss|
      normal_line(*loss)
    end
    .push(last_line)
    .join("\n")
  end

  private

  def normal_line(lack, forfeit)
    "For want of a #{lack} the #{forfeit} was lost."
  end

  def last_line
    "And all for the want of a #{formatted_qualifier}#{first_lack}."
  end

  def first_lack
    @consequences.first
  end

  def formatted_qualifier
    @qualifier + " " if @qualifier
  end
end
