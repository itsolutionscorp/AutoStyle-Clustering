module SpeakingTone
  def interpret_tone(remark)
    return :silence  if remark.strip.empty?
    return :shouting if remark == remark.upcase
    return :question if remark.strip.end_with? '?'
    :normal
  end
end

class Bob
  include SpeakingTone

  def hey(remark)
    case interpret_tone(remark)
      when :silence  then 'Fine. Be that way!'
      when :shouting then 'Woah, chill out!'
      when :question then 'Sure.'
      else 'Whatever.'
    end
  end
end
