class Proverb
  def initialize(*args)
    @qualifier_hash = args.last.is_a?(Hash) ? args.pop : {}
    @consequences = args
  end

  def consequence_pairs
    @consequences.zip(@consequences[1..-1])[0..-2]
  end

  def root_cause
    @qualifier_hash[:qualifier] ? "#{@qualifier_hash[:qualifier]} #{@consequences.first}" : @consequences.first
  end

  def proverb
    consequence_chain = consequence_pairs.inject("") do |acc, pair|
      acc + "For want of a #{pair.first} the #{pair.last} was lost.\n"
    end
    consequence_chain + "And all for the want of a #{root_cause}."
  end

  def to_s
    proverb
  end
end
