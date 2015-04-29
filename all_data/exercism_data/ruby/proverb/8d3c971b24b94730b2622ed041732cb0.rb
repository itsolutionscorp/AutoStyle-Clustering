class Proverb
  attr_reader :objects_of_desire, :qualifier

  def initialize(*objects_of_desire, qualifier: "")
    @objects_of_desire = objects_of_desire
    @qualifier = qualifier
  end

  def to_s
    lines = []
    chain_of_events.each do |chain|
      lines << consequence(chain[:want], chain[:lost])
    end
    lines << root_cause
    lines.join("\n")
  end

  private

  def chain_of_events
    chain = []
    0.upto(objects_of_desire.length - 2).each do |i|
      chain << {want: objects_of_desire[i], lost: objects_of_desire[i + 1]}
    end
    chain
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
