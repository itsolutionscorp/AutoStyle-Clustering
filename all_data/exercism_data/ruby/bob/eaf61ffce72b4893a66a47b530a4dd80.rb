class Bob
  def hey(what_was_said)
    what_was_said = SpokenWords.new(what_was_said)
    bobs_reply = case
      when what_was_said.asking?    then  'Sure.'
      when what_was_said.shouting?  then  'Woah, chill out!'
      when what_was_said.silence?   then  'Fine. Be that way!'
      else
        'Whatever.'
    end
  end
end

class SpokenWords < Struct.new(:what_was_said)

  def silence?  ; what_was_said.strip.empty?         end
  def asking?; !shouting? && question?   end
  def question? ; what_was_said.end_with?('?')       end

  def shouting?
    !the_alpha_chars.empty? && the_alpha_chars.upcase == the_alpha_chars
  end

private
  def the_alpha_chars
    what_was_said.tr('^A-Za-z', '')
  end
end
