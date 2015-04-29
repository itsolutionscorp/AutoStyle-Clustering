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
    @things[0..-2].each_with_index do |thing, index|
      proverb += statement(thing, @things[index +1])
    end
    proverb += conclusion(@things[0])
  end

  public
  def to_s
    @proverb ||= proverb
  end
end
