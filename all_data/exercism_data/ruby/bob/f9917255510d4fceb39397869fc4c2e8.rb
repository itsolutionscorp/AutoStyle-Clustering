module Responder
  def hey(words)
    responses[Statement.get_type(words)]
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
  def self.get_type(words)
    TYPES.each do |type, condition| 
      return type if condition.call(words)
    end
  end

  TYPES = {
    :nothing  => ->(words) { words.to_s.empty? },
    :yelling  => ->(words) { words.eql?(words.upcase) },
    :question => ->(words) { words.end_with?("?") },
    :default  => ->(words) { true }
  }
end
