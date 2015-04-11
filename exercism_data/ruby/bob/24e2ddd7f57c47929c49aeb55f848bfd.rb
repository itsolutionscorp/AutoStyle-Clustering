module Responder
  def hey(words)
    responses[Statement::Builder.build(words).type]
  end
end

module Teenager
  include Responder

  def responses
    {
      :nothing => "Fine. Be that way.",
      :yelling => "Woah, chill out!",
      :question => "Sure.",
      :default =>  "Whatever."
    }
  end
end

class Bob
  include Teenager
end

module Statement
  module Builder
    def self.build(words)
      TYPES.keys.each do |type| 
        type_class = Object.const_get(type)
        return type_class.new if type_class.is_type?(words) 
      end
    end
  end

  TYPES = {
    "Nothing" => ->(words)  { words.to_s.empty? },
    "Yelling" => ->(words)  { words.eql?(words.upcase) },
    "Question" => ->(words) { words.end_with?("?") },
    "Default" => ->(words)  { true }
  }

  TYPES.each do |type, type_lambda|
    Object.const_set(type, Struct.new(:statement) do 
      def self.is_type?(words)
        TYPES[self.to_s].call(words)
      end  

      def type
        self.class.to_s.downcase.to_sym
      end
    end)
  end
end
