# -*- coding: utf-8 -*-
class Bob

  def hey(text)
    analyzer = StatementAnalyzer.new(text.to_s.strip)

    if analyzer.says_nothing?
      'Fine. Be that way!'
    elsif analyzer.yelling?
      'Woah, chill out!'
    elsif analyzer.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

class StatementAnalyzer

  def initialize(text)
    @text = text
  end

  def says_nothing?
    @text.empty?
  end

  def yelling?
    @text == @text.upcase
  end

  def asking?
    @text.end_with?('?')
  end

end
