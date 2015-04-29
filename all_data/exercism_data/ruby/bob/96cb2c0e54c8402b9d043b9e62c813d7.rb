require 'pry'
require 'pry-nav'

class Bob

  # Say something to Bob
  def hey(text)
    type = parse(text)
    respond_to(type)
  end

private

  # Figure out what kind of statement was made to Bob
  def parse(text='')
    case text
    when /^[^a-z]+$/  # statement contains no lowercase letters
      :yell
    when /\?$/        # statement ends in a question mark
      :question
    when /^\s*$/      # statement contains only whitespace characters
      :nothing
    when nil          # regexes don't like nil so we have a special check
      :nothing
    else              # anything else is a statement
      :statement
    end
  end

  # Respond to a type of statement accordingly
  def respond_to(type)
    case type
    when :question
      'Sure.'
    when :statement
      'Whatever.'
    when :yell
      'Woah, chill out!'
    when :nothing
      'Fine. Be that way.'
    end
  end

end
