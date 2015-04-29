class Proverb
  def initialize(*args, **qualifier)
    @qualifier = qualifier
    @consequences = args
  end

  def consequence_pairs
    @consequences.each_cons(2)
  end

  def root_cause
    @qualifier[:qualifier] ? "#{@qualifier[:qualifier]} #{@consequences.first}" : @consequences.first
  end

  def to_s
    consequence_pairs.inject("") { |acc, (wanted_item, lost_item)| acc + "For want of a #{wanted_item} the #{lost_item} was lost.\n" }
                     .concat("And all for the want of a #{root_cause}.")
  end
end
