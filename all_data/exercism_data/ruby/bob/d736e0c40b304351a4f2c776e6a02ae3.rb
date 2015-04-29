class Bob
  attr_reader :responses, :text

  def initialize
    @responses = {
      silent: 'Fine. Be that way!',
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      default: 'Whatever.'
    }
  end

  def hey(input)
    @text = normalize_text input
    appropriate_response
  end

  private

  def normalize_text(input)
    input.to_s.strip
  end

  def appropriate_response
    responses.fetch response_key
  end

  def response_key
    if empty_string?
      :silent
    elsif no_lower_letters?
      :shouting
    elsif ends_in_huh?
      :question
    else
      :default
    end
  end

  def empty_string?
    text.empty?
  end

  def no_lower_letters?
    text.match /^[^a-z]+$/
  end

  def ends_in_huh?
    text.end_with? '?'
  end
end
