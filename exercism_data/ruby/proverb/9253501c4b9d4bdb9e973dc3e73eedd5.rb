class Proverb
  attr_reader :chain, :options

  def initialize(*chain, **options)
    @chain = chain
    @options = options
  end

  def to_s
    cause_and_effect
  end

  private
  def cause_and_effect
    proverb = ''
    each_sentence do |sentence|
      proverb += sentence
    end
    proverb
  end

  def each_sentence
    each_word do |cause, effect|
      yield "For want of a %s the %s was lost.\n" % [cause, effect]
    end
    yield "And all for the want of a %s." % root_cause
  end

  def root_cause
    "%s%s" % [qualifier, chain.first]
  end

  def qualifier
    if options.has_key?(:qualifier)
      "%s " % options[:qualifier]
    end
  end

  def each_word
    chain[1..-1].each_with_index do |cause, index|
      yield chain[--index], cause
    end
  end
end
