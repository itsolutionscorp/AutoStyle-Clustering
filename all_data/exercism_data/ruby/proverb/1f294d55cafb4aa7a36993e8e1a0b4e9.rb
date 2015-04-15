class Proverb
  def initialize(*args, **qualifier)
    @qualifier = qualifier
    @consequences = args
  end

  def consequence_pairs
    @consequences.each_cons(2)
  end

  def root_cause
    "#{@qualifier[:qualifier]} #{@consequences.first}".lstrip
  end

  def to_s
    consequence_pairs.map { |wanted_item, lost_item| "For want of a #{wanted_item} the #{lost_item} was lost." }
                     .concat(["And all for the want of a #{root_cause}."])
                     .join("\n")
  end
end
