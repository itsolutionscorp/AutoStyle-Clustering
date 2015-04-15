module StringHelpers
  refine String do
    def blank?
      self.to_s.strip == ""
    end

    def question?
      self.end_with? "?"
    end

    def all_caps?
      self == self.upcase
    end
  end
end

class Responses

  def initialize(person_type)
    case person_type
    when :teenager
      @response = {
        blank: "Fine. Be that way!",
        all_caps: "Woah, chill out!",
        question: "Sure.",
        default: "Whatever."
      }
    else
      @response = {}
    end
  end

  def method_missing(name, *args, &block)
    name = name.to_s.match(/(.*)_response/)[1]
    if name
      if valid_keys.include? name
        return self.send(:response, name)
      end
    else
      super(name, *args, &block)
    end
  end

  def respond_to_missing(name, include_private = false)
    true if name.to_s.match(/(.*)_response$/)[1] && valid.keys.include?(name.to_sym)
  end

  private
  def valid_keys
    @response.keys.to_s
  end

  def response(type)
    @response[type.to_sym] || "I don't have a response for this type"
  end
end


using StringHelpers
class Bob
  def hey(statement)
    statement = statement.to_s # sanity check to make sure we have one even if nil
    teen = Responses.new :teenager

    return teen.blank_response    if statement.blank?
    return teen.all_caps_response if statement.all_caps?
    return teen.question_response if statement.question?
    teen.default_response
  end
end
