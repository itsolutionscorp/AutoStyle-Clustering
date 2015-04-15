module EmotionHelper
  # Determines the emotion of a message
  # Returns a symbol of the emotion
  def emotion(text)
    if text.upcase == text and text.gsub(/[a-zA-Z]/, '') != text
      :angry
    elsif text[-1] == '?'
      :query
    elsif text.gsub(/[\s)(]/,'').empty?
      :taciturn
    else
      :indifferent
    end
  end
end

class Bob
  include EmotionHelper

  attr_accessor :responses

  def initialize
    @responses = { angry: 'Woah, chill out!', query: 'Sure.',
      taciturn: 'Fine. Be that way!', indifferent: 'Whatever.' }
  end

  def hey(text)
    responses[emotion(text)]
  end
end
