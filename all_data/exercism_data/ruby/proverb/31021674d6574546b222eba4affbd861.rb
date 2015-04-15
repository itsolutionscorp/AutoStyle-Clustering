class Proverb
  attr_reader :words, :qualifier

  def initialize(*words, **qualifier)
    @words = words
    @qualifier = qualifier.empty? ? "" : " " + qualifier[:qualifier]
  end

  def to_s
    proverb_body = (build_proverb(words))
    finish_proverb(proverb_body)
  end

  private

  def build_proverb(words)
    words.each_cons(2).map do |set|
      create_line(set.first, set.last)
    end
  end

  def finish_proverb(proverb_body)
    proverb_body << "And all for the want of a#{qualifier} #{words.first}."
    proverb_body.join
  end

  def create_line(first, last)
    "For want of a #{first} the #{last} was lost.\n"
  end
end
