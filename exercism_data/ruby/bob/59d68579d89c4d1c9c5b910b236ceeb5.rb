class Responder
  private
  class << self
    def responses(*collection)
      attr_reader *collection
      collection.each do |response|
        (class << self; self; end).instance_eval do
          define_method(response) do |val|
            @responses||={}
            @responses[response]=val
          end
        end
      end
      class_eval do
        define_method(:initialize) do
          self.class.responses.each do |k,v|
            instance_variable_set("@#{k}",v)
          end
        end
      end
      @responses
    end
  end

  def silence?(comment)
    comment.strip.empty? && silence
  end

  def yell?(comment)
    comment.upcase==comment && comment.match(/[A-Z]/) && yell
  end

  def question?(comment)
    comment.match(/\?\Z/) && question
  end

  responses :silence, :yell, :question, :default

  public
  def hey(comment)
    silence?(comment) || yell?(comment) || question?(comment) || default
  end
end

class Bob < Responder
  silence 'Fine. Be that way!'
  yell 'Whoa, chill out!'
  question 'Sure.'
  default 'Whatever.'
end

class Jill < Responder
  silence 'You still here?'
  yell 'Easy tiger!'
  question 'Yeah, yeah, yeah.'
  default "That's what she said!"
end
