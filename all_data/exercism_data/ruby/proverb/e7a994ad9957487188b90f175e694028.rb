class Proverb
  attr_reader :objects_of_desire, :qualifier

  def initialize(*objects_of_desire, qualifier: "")
    @objects_of_desire = objects_of_desire
    @qualifier = qualifier
  end

  def to_s
    lines = chain_of_events.collect do |chain|
      consequence(chain[:want], chain[:lost])
    end
    lines << root_cause
    lines.join("\n")
  end

  private

  def chain_of_events
    objects_of_desire.each_cons(2).map do |a, b|
      {want: a, lost: b}
    end
  end

  def consequence(wanted_object, lost_object)
     "For want of a #{wanted_object} the #{lost_object} was lost."
  end

  def root_cause
    "And all for the want of a #{qualified_root_object}."
  end

  def qualified_root_object
    "#{qualifier} #{objects_of_desire.first}".strip
  end
end
