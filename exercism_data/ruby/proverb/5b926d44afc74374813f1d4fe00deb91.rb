class Proverb
  attr_reader :words, :qualifier

  def initialize(*words, **qualifier)
    @words = words
    @qualifier = qualifier.empty? ? "" : " " + qualifier[:qualifier]
  end

  def to_s
    build_proverb(words).join
  end

  private

  def build_proverb(words)
    proverb_body = words.each_cons(2).map do |set|
      create_line(set.first, set.last)
    end
    add_last_line(proverb_body)
  end

  def create_line(first, last)
    "For want of a #{first} the #{last} was lost.\n"
  end

  def add_last_line(proverb_body)
    proverb_body << "And all for the want of a#{qualifier} #{words.first}."
  end
end
