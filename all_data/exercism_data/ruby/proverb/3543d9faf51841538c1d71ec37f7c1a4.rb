class Proverb
  attr_reader :consequence_pairs, :root_cause

  def initialize(*args)
    qualifier_hash = args.last.is_a?(Hash) ? args.pop : {}
    @consequence_pairs = args.zip(args[1..-1])[0..-2]
    @root_cause = qualifier_hash[:qualifier] ? "#{qualifier_hash[:qualifier]} #{args.first}" : args.first
  end

  def to_s
    consequence_chain = consequence_pairs.inject("") do |acc, pair|
      acc + "For want of a #{pair.first} the #{pair.last} was lost.\n"
    end
    consequence_chain + "And all for the want of a #{root_cause}."
  end
end
