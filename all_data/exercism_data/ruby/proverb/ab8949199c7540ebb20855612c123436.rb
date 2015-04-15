class Proverb
  attr_reader :objects_of_desire, :qualifier

  def initialize(*objects_of_desire, qualifier: "")
    @objects_of_desire = objects_of_desire
    @qualifier = qualifier
  end

  def to_s
    lines = chain_of_consequences
    lines << root_cause
    lines.join("\n")
  end

  private

  def chain_of_consequences
    chain_of_objects.map do |chain|
      consequence(chain[:want], chain[:lost])
    end
  end

  def chain_of_objects
    objects_of_desire.each_cons(2).map do |wanted_object, lost_object|
      {want: wanted_object, lost: lost_object}
    end
  end

  def consequence(wanted_object, lost_object)
     "For want of a #{wanted_object} the #{lost_object} was lost."
  end

  def qualified_root_object
    "#{qualifier} #{root_object}".strip
  end

  def root_cause
    "And all for the want of a #{qualified_root_object}."
  end

  def root_object
    objects_of_desire.first
  end
end
