class Bob

  def hey msg
    case Message[msg].kind
    when :tell then 'Whatever.'
    when :yell then 'Woah, chill out!'
    when :ask  then 'Sure.'
    when :none then 'Fine. Be that way.'
    else '...'
    end
  end

  class Message < Struct.new :text

    def kind
      KINDS.find { |x| send x }
    end

    KINDS = %i[none yell tell ask]

    def yell ; text.upcase == text
    end
    def tell ; text.chars.last =~ /[\.!]/
    end
    def ask  ; text.chars.last == '?'
    end
    def none ; text.nil? || text == ''
    end
  end

end
