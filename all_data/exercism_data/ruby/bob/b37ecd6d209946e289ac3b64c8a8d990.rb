class Bob
  def hey(remark)
    case interpret_tone(remark)
      when :silence  then 'Fine. Be that way!'
      when :shouting then 'Woah, chill out!'
      when :question then 'Sure.'
      else 'Whatever.'
    end
  end

  def interpret_tone(remark)
    case 
      when remark.strip.empty?         then :silence
      when remark == remark.upcase     then :shouting
      when remark.strip.end_with?('?') then :question
      else :normal
    end
  end
end
