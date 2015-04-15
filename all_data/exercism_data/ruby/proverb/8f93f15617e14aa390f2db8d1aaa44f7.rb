class Proverb
  def initialize(*things_lost_and_opts)
    @things_lost, @qualifier = extract_qualifier(things_lost_and_opts)
  end

  def to_s
    rhyme_pairs.inject("") do |proverb, pair|
      proverb += "For want of a #{pair.first} the #{pair.last} was lost.\n"
    end + "And all for the want of a #{@qualifier}#{@things_lost.first}."
  end

  private

  def extract_qualifier(args)
    opts = args.last.is_a?(Hash) ? args.pop : {}
    opts[:qualifier] += " " unless opts[:qualifier].nil?
    [ args, opts[:qualifier] ]
  end

  def rhyme_pairs
    @things_lost.each_cons(2)
  end
end
