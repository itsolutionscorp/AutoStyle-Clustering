class Bob

  class Statement < String
    def blank?
      strip.empty?
    end

    def shouting?
      upcase.eql?(self)
    end

    def asking?
      end_with?('?')
    end
  end

  def hey(raw_msg, msg = Statement.new(raw_msg.to_s))
    case
    when msg.blank?    then 'Fine. Be that way!'
    when msg.shouting? then 'Woah, chill out!'
    when msg.asking?   then 'Sure.'
    else                    'Whatever.'
    end
  end

end
