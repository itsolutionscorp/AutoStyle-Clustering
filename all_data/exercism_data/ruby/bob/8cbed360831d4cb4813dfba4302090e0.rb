# encoding: utf-8

class Bob
  def hey(text)
    respond_to_blank(text) ||
      respond_to_shouting(text) ||
      respond_to_question(text) ||
      catch_all_response
  end

  private

  def respond_to_blank(text)
    'Fine. Be that way!' if text.nil? || text.strip.empty?
  end

  def respond_to_shouting(text)
    'Woah, chill out!' if text == text.upcase
  end

  def respond_to_question(text)
    'Sure.' if text.end_with?('?')
  end

  def catch_all_response
    'Whatever.'
  end
end
