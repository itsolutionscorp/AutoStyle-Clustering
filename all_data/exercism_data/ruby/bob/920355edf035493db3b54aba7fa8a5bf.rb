module Prompt
  def yelling? what_was_said
    what_was_said == what_was_said.upcase
  end

  def question? what_was_said
    what_was_said.end_with? '?'
  end

  def boring? what_was_said
    what_was_said.nil? or what_was_said.empty?
  end
end


class Bob
  include Prompt
  def hey(prompt)

    if boring? prompt
      'Fine. Be that way.'
    elsif yelling? prompt
      'Woah, chill out!'
    elsif question? prompt
      'Sure.'
    else
      'Whatever.'
    end
  end
end
