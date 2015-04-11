# Bob is in the news!
#
# http://www.theonion.com/video/braindead-teen-only-capable-of-rolling-eyes-and-te,27225/
#
class Bob
  attr_accessor :input, :words

  # Given a string of text, Bob will say something bothersome and piss you off.
  # As a side benefit, he should pass the provided test suite.
  #
  def hey(input)
    @input = input.to_s

    select_response
  end

  private
  # vague priority ordering inferred from test suite. this will grow
  # unwieldy if we get many more challenges.
  #
  def select_response
    response ||= chill! if shouting?
    
    # these are effectively redundant *today* but a slightly altered test suite
    # might make us glad to have them explicitly called out.
    response ||= chill! if shouting? && questioning?
    response ||= chill! if shouting? && numbers?
    response ||= chill! if shouting? && gibberish?

    response ||= "Sure." if questioning?

    response ||= default_response if forceful?

    # overrides
    response = default_response if acronyms?
    response = 'Fine. Be that way.' unless input =~ /[a-zA-Z]/

    response || default_response
  end

  def default_response
    'Whatever.'
  end

  def chill!
    'Woah, chill out!'
  end

  def gibberish?
    gibberish_threshold = 10.0

    special_chars.count > input.length / gibberish_threshold
  end

  def questioning?
    input.include? '?'
  end

  def forceful?
    input.include? '!'
  end

  def acronyms?
    words.select{ |w| w.upcase == w }.any? && words.select{ |w| w.upcase != w }.any?
  end

  def shouting?
    input.split.select{ |w| w.upcase == w }.any?
  end

  def numbers?
    !!(input =~ /[0-9]/)
  end

  def special_chars
    input.chars.select{ |char| char =~ /[^0-9a-zA-Z]/ }
  end

  def words
    @words_memo ||= input.split
  end
end
