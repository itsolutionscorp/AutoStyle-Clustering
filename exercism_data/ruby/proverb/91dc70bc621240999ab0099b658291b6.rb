class Proverb
  attr_reader :args, :qualifier

  def initialize(*args, **qualifier)
    @args = args
    @qualifier = qualifier.empty? ? "" : " " + qualifier[:qualifier]
  end

  def to_s
    finish_proverb(build_proverb(create_word_sets))
  end

  private

  def create_word_sets
    args.each_cons(2).map do |args|
      args
    end
  end

  def build_proverb(word_sets)
    word_sets.map do |set|
      create_line(set.first, set.last)
    end
  end

  def finish_proverb(proverb)
    proverb << "And all for the want of a#{qualifier} #{args.first}."
    proverb.join
  end

  def create_line(first, last)
    "For want of a #{first} the #{last} was lost.\n"
  end
end
