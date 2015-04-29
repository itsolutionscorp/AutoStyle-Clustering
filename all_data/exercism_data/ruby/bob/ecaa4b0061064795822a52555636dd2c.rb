# with hash
#
class Bob_1

  def hey msg
    case KINDS.find msg
    when :tell then 'Whatever.'
    when :yell then 'Woah, chill out!'
    when :ask  then 'Sure.'
    when :none then 'Fine. Be that way.'
    else '...'
    end
  end

  KINDS = {
    none: -> _ {  nil? || empty?         },
    yell: -> _ {  upcase == self         },
    tell: -> _ {  end_with? *'.!'.chars  },
    ask:  -> _ {  end_with? '?'          }
  }

  def KINDS.find who
    super { |_,proc| who.instance_eval &proc }.first
  end
end


# with module
#
class Bob_2

  def hey msg
    case Kinds.find msg
    when :tell then 'Whatever.'
    when :yell then 'Woah, chill out!'
    when :ask  then 'Sure.'
    when :none then 'Fine. Be that way.'
    else '...'
    end
  end

  module Kinds # order matters
    def none; nil? || empty?         end
    def yell; upcase == self         end
    def tell; end_with? *'.!'.chars  end
    def ask;  end_with? '?'          end

    def self.find who
      instance_methods.find { |x|
        instance_method(x).bind(who).call }
    end
  end
end


# with Message class
#
class Bob_3

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

    def none; empty?                 end
    def yell; upcase == self         end
    def tell; end_with? *'.!'.chars  end
    def ask;  end_with? '?'          end
  end
end


Bob = ObjectSpace.each_object(Class).select {
  |x| x.name =~ /Bob_\d+$/ }.sample

puts "-- The #{Bob} is here --"
