class Proverb
  attr_reader :words, :count, :qualifier

  def initialize(*words)
    options = words.last.is_a?(Hash) ? words.pop : {}
    @qualifier = options.fetch(:qualifier, "")
    @words = words
    @count = words.count
  end

  def to_s
    combine_repeat_lines + last_line
  end

  private

  def combine_repeat_lines
    (1..count-1).reduce("") do |line, i|
      line + repeat_line(words[i-1], words[i])
    end
  end

  def repeat_line(obj_1, obj_2)
    "For want of a #{obj_1} the #{obj_2} was lost.\n" 
  end

  def last_line
    "And all for the want of a #{qualifier?}#{words.first}."
  end

  def qualifier?
    qualifier.empty? ? qualifier : qualifier + " "
  end
end
