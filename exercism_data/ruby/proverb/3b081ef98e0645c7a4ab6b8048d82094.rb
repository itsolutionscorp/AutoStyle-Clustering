class Proverb

  def initialize(*args, **opts)
    opts ||= {}
    @lines = generate_lines(args, opts[:qualifier])
  end

  def to_s
    @lines.join "\n"
  end

  def generate_lines(words, qualifier)
    word_pairs = []
    words.each_with_index do |word, index|
      word_pairs << [word, words[(index + 1)]] unless (index+1) == words.length
    end
    qualifier = qualifier ? qualifier + ' ' : ''
    lines = word_pairs.map{ |first, second| "For want of a #{first} the #{second} was lost."}
    lines.push "And all for the want of a #{qualifier}#{words.first}."
  end

end
