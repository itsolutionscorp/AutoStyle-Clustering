#
# John Barker <john@groopt.com>
# 10/5/2013
#

class Bob
  attr_accessor :saying

  def hey(saying)
    @saying = saying.gsub("\n", "").strip

    if is_question?
      'Sure.'
    elsif is_all_caps?
      'Woah, chill out!'
    elsif is_empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def is_all_caps?
    contains_letters? && saying.upcase == saying
  end

  def is_question?
    # Check for all caps since it takes precedence
    !is_all_caps? && saying.match(/\?$/)
  end

  def is_empty?
    saying.empty?
  end

  def contains_letters?
    saying.match /[a-zA-Z]/
  end

end
