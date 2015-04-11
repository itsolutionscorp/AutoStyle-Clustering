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

  def response(type)
    @response[type] || "I don't have a response"
  end
end


using StringHelpers
class Bob
  def hey(string)
    string = string.to_s # sanity check to make sure we have one even if nil
    teen = Responses.new :teenager

    return teen.response(:blank)    if string.blank?
    return teen.response(:all_caps) if string.all_caps?
    return teen.response(:question) if string.question?
    teen.response(:default)
  end
end
