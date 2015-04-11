class Proverb
  def initialize(*things_lost_and_opts)
    @things_lost, @qualifier = extract_qualifier(things_lost_and_opts)
  end

  def to_s
    lines + final_line
  end

  private
  
  def extract_qualifier(args)
    opts = args.last.is_a?(Hash) ? args.pop : {}
    opts[:qualifier] += " " unless opts[:qualifier].nil?
    [ args, opts[:qualifier] ]
  end

  def lines
    rhyme_pairs.inject("") do |lines, pair|
      lines += "For want of a #{pair.first} the #{pair.last} was lost.\n"
    end
  end

  def final_line
    "And all for the want of a #{@qualifier}#{@things_lost.first}."
  end

  def rhyme_pairs
    @things_lost.each_cons(2)
  end
end
