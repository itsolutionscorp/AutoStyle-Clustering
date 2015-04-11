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
    text.nil? || text.strip.empty? ? 'Fine. Be that way!' : nil
  end

  def respond_to_shouting(text)
    text == text.upcase ? 'Woah, chill out!' : nil
  end

  def respond_to_question(text)
    text.end_with?('?') ? 'Sure.' : nil
  end

  def catch_all_response
    'Whatever.'
  end
end
