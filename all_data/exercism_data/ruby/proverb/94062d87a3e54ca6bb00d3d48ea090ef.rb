class Proverb
  def initialize(*objects_of_desire, qualifier: nil)
    @objects_of_desire = objects_of_desire
    @qualifier = qualifier
  end

  def to_s
    [*lines, closing_line].join("\n")
  end

  private

  def lines
    objects_of_desire.each_cons(2).map { |desired_object, lost_object|
      "For want of a #{desired_object} the #{lost_object} was lost."
    }
  end

  def closing_line
    "And all for the want of a #{original_object_of_desire}."
  end

  def original_object_of_desire
    [qualifier, objects_of_desire.first].compact.join(" ")
  end

  attr_reader :objects_of_desire, :qualifier
end
