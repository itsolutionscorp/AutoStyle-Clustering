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
  # here I exchanged usage of minimal subset of ruby
  # for DRYer, more readable code
  # but having some curly braces and commas,
  # not sure what is better
  #
  class Message < Struct.new :text

    def kind
      KINDS.find { |_,proc|
        text.instance_eval &proc
      }.first
    end

    # order matters
    KINDS = {
      none: -> _ {  nil? || empty?         },
      yell: -> _ {  upcase == self         },
      tell: -> _ {  end_with? *'.!'.chars  },
      ask:  -> _ {  end_with? '?'          }
    }
  end
end
