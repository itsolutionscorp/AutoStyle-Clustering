class Proverb
  def initialize(*args, **qualifier)
    @args = args
    @qualifier = qualifier.empty? ? "" : " " + qualifier[:qualifier]
  end

  def to_s
    build_proverb
  end

  def build_proverb
    word_sets = []
    @args.each_cons(2) do |args|
      word_sets << args
    end
    construct_lines(word_sets)
  end

  def construct_lines(word_sets)
    proverb = word_sets.each_with_object([]) do |(first, last), lines|
      lines << "For want of a #{first} the #{last} was lost.\n"
    end
    proverb << "And all for the want of a#{@qualifier} #{@args[0]}."
    proverb.join
  end
end
