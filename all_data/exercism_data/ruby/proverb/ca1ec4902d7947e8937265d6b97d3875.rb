class Proverb
  attr_reader :chain

  def initialize(*chain)
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
    if chain.last.is_a?(Hash)
      "#{qualifier} #{root}"
    else
      root
    end
  end

  def qualifier
    chain.last.values.first
  end

  def root
    chain[0]
  end

  def each_word
    chain[1..-1].each_with_index do |cause, index|
      unless cause.is_a?(Hash)
        yield chain[--index], cause
      end
    end
  end
end
