# -*- coding: utf-8 -*-
class Bob

  def hey(text)
    text = "#{text}".strip

    if says_nothing?(text)
      'Fine. Be that way!'
    elsif yelling?(text)
      'Woah, chill out!'
    elsif asking?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

    def says_nothing?(text)
      text.empty?
    end

    def yelling?(text)
      text == text.upcase
    end

    def asking?(text)
      text.end_with?('?')
    end

end
