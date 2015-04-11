class Proverb

  attr_reader :chain, :options
  attr_accessor :proverb 

  def initialize(*chain)
    sanitized(chain)
    @chain = chain
    @proverb = []
  end

  def to_s
    (chain.length - 1).times do |i|
      proverb << "For want of a #{chain[i]} the #{chain[i + 1]} was lost.\n"
    end
    proverb.join + "And all for the want of a #{qualifier}nail."
   end

   def qualifier
    options[:qualifier] ? options[:qualifier] + ' ' : ''
  end

  private

  def sanitized(chain)
    if chain.last.class == Hash 
      @options = chain.pop
    else
      @options = {}
    end
  end

end
