class Proverb

  attr_reader :built_proverb

  def initialize(*words)
    extract_words_and_qualifier(words)
  end

  def extract_words_and_qualifier(args)
    @words = args.select{|a| a.is_a?(String)}
    a = args.find{|a| a.is_a? Hash}
    @qualifier = a[:qualifier] if a && a[:qualifier]
  end

  def build_proverb
    a = []
    pairs = @words.each_cons(2)

    a << pairs.collect do |pair|
      template(pair[0],pair[1])
    end

    ending_plus_qualifier = [@qualifier, @words.first].compact.join(" ")
    a << ending(ending_plus_qualifier)

    @built_proverb = a.flatten.join("\n")
  end

  def to_s
    @built_proverb || build_proverb
  end

  def template(a,b)
    "For want of a #{a} the #{b} was lost."
  end

  def ending(a)
    "And all for the want of a #{a}."
 end

end
