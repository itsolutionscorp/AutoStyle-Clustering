class Proverb

  def initialize(*chain, qualifier: '')
    @qualifier = qualifier.empty? ? '' : qualifier + " "
    @chain = chain
  end

  def to_s
    build_proverb(@chain)
  end

  private

  def build_proverb(list)
    return "And all for the want of a " + @qualifier + @chain[0] + "." if list.length == 1
    return "For want of a " + list[0] + " the " + list[1] + " was lost.\n" + build_proverb(list[1..-1])
  end

  def extract_options

  end
end
