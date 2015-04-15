class Proverb
  attr_reader :proverb, :options

  def initialize(*words)
    if words.last.is_a? Hash
      @options = words.pop
    else
      @options = {}
    end
    @proverb = generate_sequence(*words)
  end

  def to_s
    proverb.to_s
  end

  private

  def generate_sequence(*words)
    op = ""
    words.each_cons(2) do |cause, effect|
      op += "For want of a #{cause} the #{effect} was lost.\n"
    end
    op += "And all for the want of a #{qualifier}#{words.first}."
  end

  def qualifier
    options.empty? ? "" : options[:qualifier]+" "
  end

end
