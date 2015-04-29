class Proverb
  attr_reader :chain, :options

  def initialize(*chain)
    if chain.last.is_a? Hash
      @options = chain.pop
    else
      @options
    end

    @chain = chain
  end

  def to_s
    proverb = ''
    each_sentence do |sentence|
      proverb += sentence
    end
    proverb
  end

  private
  def each_sentence
    each_word do |cause, effect|
      yield "For want of a #{cause} the #{effect} was lost.\n"
    end
    yield "And all for the want of a #{root_cause}."
  end

  def root_cause
    if options
      "#{qualifier} #{root}"
    else
      root
    end
  end

  def qualifier
    options.values.first
  end

  def root
    chain.first
  end

  def each_word
    chain[1..-1].each_with_index do |cause, index|
      yield chain[--index], cause
    end
  end
end
