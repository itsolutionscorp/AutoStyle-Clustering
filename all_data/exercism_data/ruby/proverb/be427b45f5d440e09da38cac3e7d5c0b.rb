class Proverb
  def initialize(*args, **qualifier)
    @args = args
    @qualifier = qualifier.empty? ? "" : " " + qualifier[:qualifier]
  end

  def to_s
    create_word_sets
  end

  def create_word_sets
    word_sets = []
    @args.each_cons(2) do |args|
      word_sets << args
    end
    build_proverb(word_sets)
  end

  def build_proverb(word_sets)
    proverb = word_sets.each_with_object([]) do |(first, last), lines|
      lines << create_line(first, last)
    end
    proverb << "And all for the want of a#{@qualifier} #{@args[0]}."
    proverb.join
  end

  def create_line(first, last)
    "For want of a #{first} the #{last} was lost.\n"
  end
end
