module Responder
  def respond(words)
    responses[Statement.build(words).class.to_s.downcase.to_sym]
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

  def hey(words)
    respond(words)
  end
end

class Bob
  include Teenager
end

Statement = Struct.new(:words) do
  def self.types
    [Nothing, Yelling, Question, Default]
  end

  def self.build(words)
    types.each { |type| return type.new(words) if type.is_type?(words) }
  end
end

class Nothing < Statement
  def self.is_type?(words)
    words.to_s.empty?
  end
end

class Question < Statement
  def self.is_type?(words)
    words.end_with?("?")
  end
end

class Yelling < Statement
  def self.is_type?(words)
    words.eql?(words.upcase)
  end
end

class Default < Statement
  def self.is_type?(words)
    true 
  end
end
