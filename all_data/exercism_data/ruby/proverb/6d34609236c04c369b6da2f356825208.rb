class Proverb

  attr_reader :chain, :qualifier

  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualifier = qualifier
    @proverb = ""
  end

  def to_s
    assemble_lines(build_pairs)
  end

  def build_pairs
    chain.zip(chain[1..-1])
  end

  def assemble_lines(pairs)
    pairs[0..-2].each do |pair|
      @proverb += "For want of a #{pair[0]} the #{pair[1]} was lost.\n"
    end
    @proverb + proverb_last_line
  end

  def proverb_last_line
    "And all for the want of a #{qualifier} nail.".gsub("  ", " ")
  end

end
