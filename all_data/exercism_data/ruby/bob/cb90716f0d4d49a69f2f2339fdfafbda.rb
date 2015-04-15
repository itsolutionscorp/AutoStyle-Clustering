# encoding: utf-8

class Bob
  def hey(text)
    blank(text) ||
      shouting(text) ||
      question(text) ||
      catch_all
  end

  private

  def blank(text)
    if text.nil? || text.strip.empty?
      'Fine. Be that way!'
    end
  end

  def shouting(text)
    if text == text.upcase
      'Woah, chill out!'
    end
  end

  def question(text)
    if text.end_with?('?')
      'Sure.'
    end
  end

  def catch_all
    'Whatever.'
  end
end
