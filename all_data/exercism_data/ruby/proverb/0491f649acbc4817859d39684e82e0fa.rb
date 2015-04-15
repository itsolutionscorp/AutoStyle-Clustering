class Proverb

  def initialize(*chain, qualifier: nil)
    @chain = chain
    @qualifier = qualifier
    @qualifier << " " if !qualifier.nil?
  end

  def to_s
    (0..@chain.count-1).each.with_object("") do |n, song|
      if n == @chain.count-1
        song << "And all for the want of a #{@qualifier}#{@chain[0]}."
      else
        song << "For want of a #{@chain[n]} the #{@chain[n+1]} was lost.\n"
        n += 1
      end
    end
  end

end
