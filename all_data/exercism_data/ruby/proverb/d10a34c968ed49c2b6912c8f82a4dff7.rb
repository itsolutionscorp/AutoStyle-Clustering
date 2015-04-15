class Proverb
  attr_reader :proverb

  def initialize(*args, qualifier: nil)
    @proverb = verse(*args) + last_line(qualifier, args.first)
  end

  def to_s
    proverb
  end

  private
  def verse(*args)
    args.each_cons(2).map {|a, b| "For want of a #{a} the #{b} was lost.\n"}.join
  end

  def last_line(qualifier, small_thing)
    "And all for the want of a #{qualifier} #{small_thing}.".squeeze ' '
  end
end
