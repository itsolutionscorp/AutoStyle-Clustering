class Proverb
  def initialize(*matters)
    @options = matters.last.is_a?(Hash) ? matters.pop : {}
    @matters = matters
  end

  def to_s
    for_lines + last_line
  end

  private

  def first
    [@options[:qualifier], @matters.first].compact.join(' ')
  end

  def for_lines
    @matters.each_cons(2).map do |first, second|
      "For want of a #{first} the #{second} was lost.\n"
    end.join('')
  end

  def last_line
    "And all for the want of a #{first}."
  end
end
