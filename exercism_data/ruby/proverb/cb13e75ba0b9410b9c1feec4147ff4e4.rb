class Proverb

  attr_reader :chain, :options
  attr_accessor :proverb

  def initialize(*chain)
    sanitize(chain)
    @chain = chain
  end

  def to_s
    verses.join
  end

  def verses
    chain.each_cons(2).map do |cause, effect|
      "For want of a #{cause} the #{effect} was lost.\n"
    end << "And all for the want of a #{qualifier}nail."
  end

  def qualifier
    options[:qualifier] ? options[:qualifier] + ' ' : ''
  end

  private

  def sanitize(chain)
    if chain.last.class == Hash
      @options = chain.pop
    else
      @options = {}
    end
  end

end