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

#
# the nil is handled in Message.[]
#
# include + send  vs  method + bind + call
#
  class Message < String
    def self.[] msg
      new msg || ''
    end

options = [
-> {

    def kind
      Kinds.find self
    end

    module Kinds
      # order matters:
      def none; empty?                 end
      def yell; upcase == self         end
      def tell; end_with? *'.!'.chars  end
      def ask;  end_with? '?'          end

      def self.all; instance_methods end
      def self.find who
        all.find { |x| instance_method(x).bind(who).call }
      end
    end

},
-> {

    def kind
      KINDS.find { |_,proc| instance_eval &proc }.first
    end

    KINDS = {
      none: -> _ {  empty?                 },
      yell: -> _ {  upcase == self         },
      tell: -> _ {  end_with? *'.!'.chars  },
      ask:  -> _ {  end_with? '?'          }
    }
}
].sample.call

  end
end
