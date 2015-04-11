class Proverb
	def initialize(*things)
    @things = things
    @options = nil
    if @things.last.is_a?(Hash)
      @options = @things.pop
    end
	end

  private
  def statement(desired, lost)
    "For want of a #{desired} the #{lost} was lost.\n"
  end

  def conclusion(thing)
    if @options
      vanity_object = "#{@options[:qualifier]} #{thing}"
    else
      vanity_object = thing
    end
    "And all for the want of a #{vanity_object}."
  end

  def proverb
    proverb = ""
    pairs = @things[0..-2].zip(@things[1..-1])
    pairs.each do |pair|
      proverb += statement(pair.first, pair.last)
    end
    proverb += conclusion(@things.first)
  end

  public
  def to_s
    @proverb ||= proverb
  end
end
