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

    def kind
      KINDS.find { |x| send x }
    end

    KINDS = %i[none yell tell ask]

options = [ -> {    

    def none; empty?
    end
    def yell; upcase == self
    end
    def tell; end_with? *'.!'.chars
    end
    def ask;  end_with? '?'
    end

}, -> {

    {
      none: -> {  empty?                 },
      yell: -> {  upcase == self         },
      tell: -> {  end_with? *'.!'.chars  },
      ask:  -> {  end_with? '?'          }
    }.each { |name,proc| define_method name, &proc }

}, -> {    

    def none; empty?                 end
    def yell; upcase == self         end
    def tell; end_with? *'.!'.chars  end
    def ask;  end_with? '?'          end

}].sample.call

  end
end
