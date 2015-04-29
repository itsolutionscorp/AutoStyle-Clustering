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

  class Message < String
    def self.[] msg
      new msg || ''
    end

options = [ -> {    

    def kind
      KINDS.find { |x| send x }
    end

    KINDS = %i[none yell tell ask]

    def none; empty?
    end
    def yell; upcase == self
    end
    def tell; end_with? *'.!'.chars
    end
    def ask;  end_with? '?'
    end

    
}, -> {

    def kind
      KINDS.find { |x| send x }
    end

    KINDS = %i[none yell tell ask]

    def none; empty?                 end
    def yell; upcase == self         end
    def tell; end_with? *'.!'.chars  end
    def ask;  end_with? '?'          end

}, -> {

    def kind
      KINDS.find { |_,proc| instance_eval &proc }.first
    end

    KINDS = {
      none: -> _ {  empty?                 },
      yell: -> _ {  upcase == self         },
      tell: -> _ {  end_with? *'.!'.chars  },
      ask:  -> _ {  end_with? '?'          }
    }

}].sample.call

  end
end
